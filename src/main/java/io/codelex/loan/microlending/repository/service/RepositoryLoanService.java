package io.codelex.loan.microlending.repository.service;

import io.codelex.loan.microlending.LoanService;
import io.codelex.loan.microlending.api.*;

import io.codelex.loan.microlending.repository.ApplicationRecordRepository;
import io.codelex.loan.microlending.repository.LoanExtensionRecordRepository;
import io.codelex.loan.microlending.repository.LoanRecordRepository;
import io.codelex.loan.microlending.repository.UserRecordRepository;
import io.codelex.loan.microlending.repository.mapper.MapApplicationRecordToApplication;
import io.codelex.loan.microlending.repository.mapper.MapExtensionRecordToExtension;
import io.codelex.loan.microlending.repository.mapper.MapLoanRecordToLoan;
import io.codelex.loan.microlending.repository.model.ApplicationRecord;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Component
public class RepositoryLoanService implements LoanService {
    private final LoanRecordRepository loanRecordRepository;
    private final LoanExtensionRecordRepository extensionRecordRepository;
    private final UserRecordRepository userRecordRepository;
    private final ApplicationRecordRepository applicationRecordRepository;
    private final MapLoanRecordToLoan toLoan = new MapLoanRecordToLoan();
    private final MapExtensionRecordToExtension toExtension = new MapExtensionRecordToExtension();
    private final MapApplicationRecordToApplication toApplication = new MapApplicationRecordToApplication();
    private RepositoryInterestFactorService interestFactorService = new RepositoryInterestFactorService();
    private RepositoryIpService ipService = new RepositoryIpService();
    private ClockTime clockTime;


    public RepositoryLoanService(
            LoanRecordRepository loanRecordRepository,
            LoanExtensionRecordRepository extensionRecordRepository,
            UserRecordRepository userRecordRepository, ApplicationRecordRepository applicationRecordRepository, ClockTime clockTime) {
        this.loanRecordRepository = loanRecordRepository;
        this.extensionRecordRepository = extensionRecordRepository;
        this.userRecordRepository = userRecordRepository;
        this.applicationRecordRepository = applicationRecordRepository;
        this.clockTime = clockTime;
    }



    public String idGenerator() {
        Random r = new Random(System.currentTimeMillis());
        String id = String.valueOf(((1 + r.nextInt(4)) * 10000000 + r.nextInt(10000000)));
        return id.substring(0, 4) + "-" + id.substring(4);
    }

    @Override
    public Loan createLoan(Principal principal, LoanRequest request, HttpServletRequest servletRequest) {
        UserRecord userRecord = userRecordRepository.findByEmail(principal.getName());
        List<ExtensionRecord> extensionRecords = new ArrayList<>();
        LoanRecord loanRecord = new LoanRecord(
                idGenerator(),
                LoanStatus.OPEN,
                clockTime.getTime().toLocalDate(),
                clockTime.getTime().toLocalDate().plusDays(request.getDays()),
                request.getAmount(),
                interestFactorService.calculate(request.getAmount(), request.getDays()),
                request.getAmount().add(interestFactorService.calculate(request.getAmount(), request.getDays())),
                extensionRecords,
                userRecord
        );
        loanRecord = loanRecordRepository.save(loanRecord);
        return toLoan.apply(loanRecord);
    }

    public Application application(Principal principal, LoanRequest request, HttpServletRequest servletRequest) {
        ipService.addIp(servletRequest);
        UserRecord currentUser = userRecordRepository.findByEmail(principal.getName());
        if (loanRecordRepository.checkIfCurrentUserHaveLoan(currentUser)) {
            throw new IllegalStateException();
        } else if (ipService.maxAttemptsFromIpNotReached() && timeValidation(clockTime)) {
            ApplicationRecord record = new ApplicationRecord(
                    request.getAmount(),
                    request.getDays(),
                    ApplicationStatus.APPROVED
            );
            createLoan(principal, request, servletRequest);
            record = applicationRecordRepository.save(record);
            return toApplication.apply(record);
        }

        ApplicationRecord applicationRecord = new ApplicationRecord(
                request.getAmount(),
                request.getDays(),
                ApplicationStatus.REJECTED

        );
        applicationRecord = applicationRecordRepository.save(applicationRecord);
        return toApplication.apply(applicationRecord);

    }


    @Override
    public Loan findByIdAndExtend(Principal principal, String id, Integer days) {
        UserRecord currentUser = userRecordRepository.findByEmail(principal.getName());
        Constraints constraints = setConstraints();
        if (!loanRecordRepository.checkIfCurrentUserHaveLoan(currentUser)) {
            throw new NoSuchElementException();
        }
        if (!loanRecordRepository.isLoanPresent(id)
                || days == null
                || days < constraints.getMinExtensionDays()
                || days > constraints.getMaxExtensionDays()) {

            throw new NoSuchElementException();
        }
        createLoanExtension(id, days);
        LoanRecord record = loanRecordRepository.findLoanById(id);
        record.setDueDate(record.getDueDate().plusDays(days));
        record.setInterest(record.getInterest().add(interestFactorService.calculateExtensionInterest(record.getPrincipal(), days)));
        record.setTotal(record.getTotal().add(interestFactorService.calculateExtensionInterest(record.getPrincipal(), days)));

        record = loanRecordRepository.save(record);
        return toLoan.apply(record);

    }

    @Override
    public LoanExtension createLoanExtension(String id, Integer days) {
        LoanRecord record = loanRecordRepository.findLoanById(id);
        ExtensionRecord extensionRecord = new ExtensionRecord(
                clockTime.getTime().toLocalDate(),
                days,
                interestFactorService.calculateExtensionInterest(record.getPrincipal(), days),
                record
        );

        record.getExtensions().add(extensionRecord);
        extensionRecord = extensionRecordRepository.save(extensionRecord);
        return toExtension.apply(extensionRecord);
    }

    public List<Loan> findLoan(String owner) {
        UserRecord userRecord = userRecordRepository.findByEmail(owner);
        return loanRecordRepository.findLoanWithCurrentUser(userRecord)
                .stream()
                .map(toLoan)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExtensionRecord> findLoansWithExtensions(Long id) {
        return new ArrayList<>(extensionRecordRepository.findAllExtensionsForLoan(id));
    }

    private boolean timeValidation(ClockTime clockTime) {
        LocalTime currentTime = clockTime.getTime().toLocalTime();
        LocalTime endTime = LocalTime.parse("04:59:59");
        if (currentTime.isAfter(endTime) && currentTime.isBefore(LocalTime.of(21, 00))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean termValidation(LoanRequest request) {
        if (request.getDays() >= 7 && request.getDays() <= 30) {
            return true;
        }
        return false;
    }

    @Override
    public boolean amountValidation(LoanRequest request) {
        Constraints constraints = setConstraints();
        int res = request.getAmount().compareTo(constraints.getMinAmount());
        int res2 = request.getAmount().compareTo(constraints.getMaxAmount());
        return res >= 0 && res2 <= 0;
    }

    public Constraints setConstraints() {
        return new Constraints(
                new BigDecimal(100),
                new BigDecimal(500),
                7,
                30,
                7,
                30
        );
    }
}
