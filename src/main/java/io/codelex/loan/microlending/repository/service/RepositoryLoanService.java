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
import java.security.InvalidParameterException;
import java.security.Principal;
import java.time.LocalTime;
import java.util.*;

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
    private Constraints constraints;


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


    @Override
    public Loan createLoan(Principal principal, LoanRequest request, HttpServletRequest servletRequest) {
        UserRecord userRecord = userRecordRepository.findByEmail(principal.getName());
        if (checkApplication(request, servletRequest, principal.getName()).getStatus() == Status.APPROVED) {
            LoanRecord loanRecord = new LoanRecord(
                    request.getAmount(),
                    request.getDays(),
                    clockTime.getTime(),
                    clockTime.getTime().plusDays(request.getDays()),
                    interestFactorService.extendLoanInterestFactor(request.getAmount(), request.getDays()),
                    true,
                    userRecord
            );
            loanRecord = loanRecordRepository.save(loanRecord);
            return toLoan.apply(loanRecord);
        }
        throw new IllegalStateException();
    }


    @Override
    public Loan findByIdAndExtend(Long id, Long days) {
        if (loanRecordRepository.isLoanPresent(id)) {
            if (days == 7) {
                loanRecordRepository.updateRepaymentDateByWeek(id);
                createLoanExtension(id, days);
                LoanRecord record = loanRecordRepository.findLoanById(id);

                return toLoan.apply(record);
            } else if (days == 30) {
                loanRecordRepository.updateRepaymentDateByMonth(id);
                createLoanExtension(id, days);
                LoanRecord record = loanRecordRepository.findLoanById(id);

                return toLoan.apply(record);
            }
        }
        throw new NoSuchElementException("Loan is not present");
    }

    @Override
    public LoanExtension createLoanExtension(Long id, Long days) {
        LoanRecord record = loanRecordRepository.findLoanById(id);
        ExtensionRecord extensionRecord = new ExtensionRecord(
                days,
                clockTime.getTime(),
                record.getRepaymentDate().plusDays(days),
                record,
                true
        );
        extensionRecord = extensionRecordRepository.save(extensionRecord);
        return toExtension.apply(extensionRecord);
    }

    public Application checkApplication(LoanRequest request, HttpServletRequest servletRequest, String owner) {
        ipService.addIp(servletRequest);
        UserRecord currentUser = userRecordRepository.findByEmail(owner);
        if (loanRecordRepository.checkIfCurrentUserHaveLoan(currentUser)) {
            throw new IllegalStateException();
        }
        if (ipService.maxAttemptsFromIpReached() || checkIfTimeIsValid() || checkIfTermIsValid(request) || checkIfAmountIsValid(request)) {
            ApplicationRecord record = new ApplicationRecord(
                    request.getAmount(),
                    request.getDays(),
                    Status.REJECTED
            );
            record = applicationRecordRepository.save(record);
            return toApplication.apply(record);


        } else{
            ApplicationRecord applicationRecord = new ApplicationRecord(
                    request.getAmount(),
                    request.getDays(),
                    Status.APPROVED

            );
            applicationRecord = applicationRecordRepository.save(applicationRecord);
            return toApplication.apply(applicationRecord);
        }
    }


    @Override
    public List<LoanRecord> findLoanByUserEmail(String owner) {
        UserRecord userRecord = userRecordRepository.findByEmail(owner);
        return new ArrayList<>(loanRecordRepository.findLoanWithCurrentUser(userRecord));
    }

    @Override
    public List<ExtensionRecord> getLoansWithExtensions(Long id) {
        return new ArrayList<>(extensionRecordRepository.findAllExtensionsForLoan(id));
    }

    private boolean checkIfTimeIsValid() {
        LocalTime currentTime = LocalTime.now();
        LocalTime endTime = LocalTime.parse("07:00:00");
        if (currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(endTime)) {
            return true;
        }
        return false;
    }

    private boolean checkIfTermIsValid(LoanRequest request) {
        if (request.getDays() < 7 || request.getDays() > 30) {
            return true;
        }
        return false;
    }

    private boolean checkIfAmountIsValid(LoanRequest request) {
      
        return request.getAmount().compareTo(constraints.getMinAmount()) < 0
                || request.getAmount().compareTo(constraints.getMaxAmount()) > 0;
    }

}
