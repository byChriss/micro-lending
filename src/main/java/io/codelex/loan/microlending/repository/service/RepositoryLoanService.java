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
import java.math.BigInteger;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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


    public String gen() {
        Random r = new Random(System.currentTimeMillis());
        String id = String.valueOf(((1 + r.nextInt(4)) * 10000000 + r.nextInt(10000000)));
        return id.substring(0, 4) + "-" + id.substring(4);
    }

    @Override
    public Loan createLoan(Principal principal, LoanRequest request, HttpServletRequest servletRequest) {
        UserRecord userRecord = userRecordRepository.findByEmail(principal.getName());
        List<ExtensionRecord> extensionRecords = new ArrayList<>();
        LoanRecord loanRecord = new LoanRecord(
                gen(),
                LoanStatus.OPEN,
                clockTime.getTime().toLocalDate(),
                clockTime.getTime().toLocalDate().plusDays(request.getDays()),
                request.getAmount(),
                interestFactorService.extendLoanInterestFactor(request.getAmount(), request.getDays()),
                request.getAmount().add(interestFactorService.extendLoanInterestFactor(request.getAmount(), request.getDays())),
                extensionRecords,
                userRecord
        );
        loanRecord = loanRecordRepository.save(loanRecord);
        return toLoan.apply(loanRecord);
    }

    public Application checkApplication(Principal principal, LoanRequest request, HttpServletRequest servletRequest) {
        ipService.addIp(servletRequest);
        UserRecord currentUser = userRecordRepository.findByEmail(principal.getName());
        if (loanRecordRepository.checkIfCurrentUserHaveLoan(currentUser)) {
            throw new IllegalStateException();
        } else if (checkIfTermIsValid(request) && checkIfAmountIsValid(request) && ipService.maxAttemptsFromIpNotReached() && checkIfTimeIsValid(clockTime)) {
            ApplicationRecord record = new ApplicationRecord(
                    request.getAmount(),
                    request.getDays(),
                    Status.APPROVED
            );
            createLoan(principal, request, servletRequest);
            record = applicationRecordRepository.save(record);
            return toApplication.apply(record);
        }
        ApplicationRecord applicationRecord = new ApplicationRecord(
                request.getAmount(),
                request.getDays(),
                Status.REJECTED

        );
        applicationRecord = applicationRecordRepository.save(applicationRecord);
        return toApplication.apply(applicationRecord);

    }

    @Override
    public Loan findByIdAndExtend(String id, Integer days) {
        if (loanRecordRepository.isLoanPresent(id)) {
            if (days == 7 || days == 14 || days == 30) {
                loanRecordRepository.updateRepaymentDateByWeek(id, days);
                createLoanExtension(id, days);
                LoanRecord record = loanRecordRepository.findLoanById(id);

                return toLoan.apply(record);
            }
        }
        throw new NoSuchElementException("Loan is not present");
    }

    @Override
    public LoanExtension createLoanExtension(String id, Integer days) {
        LoanRecord record = loanRecordRepository.findLoanById(id);
        ExtensionRecord extensionRecord = new ExtensionRecord(
                clockTime.getTime().toLocalDate(),
                days,
                interestFactorService.extendLoanInterestFactor(record.getPrincipal(), days),
                record

        );
        record.getExtensions().add(extensionRecord);
        extensionRecord = extensionRecordRepository.save(extensionRecord);
        return toExtension.apply(extensionRecord);
    }

    // delete if no use ... ? 
    @Override
    public List<LoanRecord> findLoanByUserEmail(String owner) {
        UserRecord userRecord = userRecordRepository.findByEmail(owner);
        return new ArrayList<>(loanRecordRepository.findLoanWithCurrentUser(userRecord));
    }

    public List<Loan> findLoan(String owner) {
        UserRecord userRecord = userRecordRepository.findByEmail(owner);
        return loanRecordRepository.findLoanWithCurrentUser(userRecord)
                .stream()
                .map(toLoan)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExtensionRecord> getLoansWithExtensions(Long id) {
        return new ArrayList<>(extensionRecordRepository.findAllExtensionsForLoan(id));
    }

    private boolean checkIfTimeIsValid(ClockTime clockTime) {
        LocalTime currentTime = clockTime.getTime().toLocalTime();
        LocalTime endTime = LocalTime.parse("07:00:00");
        if (currentTime.isAfter(endTime) && currentTime.isBefore(LocalTime.MAX)) {
            return true;
        }
        return false;
    }

    private boolean checkIfTermIsValid(LoanRequest request) {
        if (request.getDays() >= 7 && request.getDays() <= 30) {
            return true;
        }
        return false;
    }


    private boolean checkIfAmountIsValid(LoanRequest request) {
        Constraints constraints = setConstraints();

        int res = request.getAmount().compareTo(constraints.getMinAmount());
        int res2 = request.getAmount().compareTo(constraints.getMaxAmount());
        return res > 0 && res2 < 0 || res == 0 || res2 == 0;
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
