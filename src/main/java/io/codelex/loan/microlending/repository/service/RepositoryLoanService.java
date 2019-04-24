package io.codelex.loan.microlending.repository.service;

import io.codelex.loan.microlending.LoanService;
import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.api.LoanRequest;

import io.codelex.loan.microlending.repository.LoanExtensionRecordRepository;
import io.codelex.loan.microlending.repository.LoanRecordRepository;
import io.codelex.loan.microlending.repository.UserRecordRepository;
import io.codelex.loan.microlending.repository.mapper.MapExtensionRecordToExtension;
import io.codelex.loan.microlending.repository.mapper.MapLoanRecordToLoan;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Transactional
@Component
public class RepositoryLoanService implements LoanService {
    private final LoanRecordRepository loanRecordRepository;
    private final LoanExtensionRecordRepository extensionRecordRepository;
    private final UserRecordRepository userRecordRepository;
    private final MapLoanRecordToLoan toLoan = new MapLoanRecordToLoan();
    private final MapExtensionRecordToExtension toExtension = new MapExtensionRecordToExtension();
    private RepositoryInterestFactorService interestFactorService = new RepositoryInterestFactorService();
    private RepositoryIpService ipService = new RepositoryIpService();
    private ClockTime clockTime;


    public RepositoryLoanService(
            LoanRecordRepository loanRecordRepository,
            LoanExtensionRecordRepository extensionRecordRepository,
            UserRecordRepository userRecordRepository, ClockTime clockTime) {
        this.loanRecordRepository = loanRecordRepository;
        this.extensionRecordRepository = extensionRecordRepository;
        this.userRecordRepository = userRecordRepository;
        this.clockTime = clockTime;
    }

    @Override
    public Loan createLoan(String owner, LoanRequest request, HttpServletRequest servletRequest) {
        ipService.addIp(servletRequest);
        UserRecord currentUser = userRecordRepository.findByEmail(owner);

        if (loanRecordRepository.checkIfCurrentUserHaveLoan(currentUser)
                || ipService.maxAttemptsFromIpReached()
                || checkIfTimeIsValid()) {
            throw new IllegalStateException();
        } else if (!checkIfTermIsValid(request) && checkIfAmountIsValid(request)) {
            throw new InvalidParameterException("Invalid term or amount");
        } else {
            UserRecord userRecord = userRecordRepository.findByEmail(owner);

            LoanRecord loanRecord = new LoanRecord(
                    request.getAmount(),
                    request.getTerm(),
                    clockTime.getTime(),
                    clockTime.getTime().plusDays(request.getTerm()),
                    interestFactorService.extendLoanInterestFactor(request.getAmount(), request.getTerm()),
                    true,
                    userRecord
            );
            loanRecord = loanRecordRepository.save(loanRecord);
            return toLoan.apply(loanRecord);
        }
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
        if (request.getTerm() == 7 || request.getTerm() == 30) {
            return true;
        }
        return false;
    }

    private boolean checkIfAmountIsValid(LoanRequest request) {
        if (request.getAmount() > 100 || request.getAmount() < 500) {
            return true;
        }
        return false;
    }
}
