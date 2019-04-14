package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.LoanService;
import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.api.LoanRequest;

import io.codelex.loan.microlending.api.User;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;

@Component
@ConditionalOnProperty(prefix = "micro-lending", name = "store-type", havingValue = "database")
public class RepositoryLoanService implements LoanService {
    private final LoanRecordRepository loanRecordRepository;
    private final LoanExtensionRecordRepository extensionRecordRepository;
    private final UserRecordRepository userRecordRepository;
    private final MapLoanRecordToLoan toLoan = new MapLoanRecordToLoan();
    private final MapUserRecordToUser toUser = new MapUserRecordToUser();
    private final MapExtensionRecordToExtension toExtension = new MapExtensionRecordToExtension();
    private RepositoryInterestFactorService interestFactorService = new RepositoryInterestFactorService();
    private RepositoryIpService ipService = new RepositoryIpService();
   

    public RepositoryLoanService(
            LoanRecordRepository loanRecordRepository,
            LoanExtensionRecordRepository extensionRecordRepository,
            UserRecordRepository userRecordRepository) {
        this.loanRecordRepository = loanRecordRepository;
        this.extensionRecordRepository = extensionRecordRepository;
        this.userRecordRepository = userRecordRepository;
    }

    @Override
    public Loan createLoan(LoanRequest request, HttpServletRequest servletRequest){
                LoanRecord loanRecord = new LoanRecord();
                loanRecord.setAmount(request.getAmount());
                loanRecord.setTerm(request.getTerm());
                loanRecord.setApprovalDate(LocalDate.now());
                loanRecord.setRepaymentDate(LocalDate.now().plusDays(request.getTerm()));
                loanRecord.setExtendAmount(interestFactorService.extendLoanInterestFactor(request.getAmount(), request.getTerm()));
                loanRecord.setStatus(true);

                loanRecord = loanRecordRepository.save(loanRecord);

                return toLoan.apply(loanRecord);
    }

    @Override
    public Loan findByIdAndExtend(Long id, Long days) {
        if (loanRecordRepository.isLoanPresent(id)) {
            createLoanExtension(id,days);
        }
        return null;
    }

    @Override
    public LoanExtension createLoanExtension(Long id, Long days) {
        ExtensionRecord extensionRecord = new ExtensionRecord();
        extensionRecord.setExtendDays(days);
        extensionRecord.setExtensionDate(LocalDate.now());
        extensionRecord.setPaybackDate(LocalDate.now().plusDays(days));
        extensionRecord.setStatus(true);
        extensionRecord = extensionRecordRepository.save(extensionRecord);
        return toExtension.apply(extensionRecord);
    }

    @Override
    public Loan findLoanById(Long id) {
        return loanRecordRepository.findById(id)
                .map(toLoan)
                .orElseThrow(NoSuchElementException::new);
    }

    private boolean checkTime() {
        LocalTime currentTime = LocalTime.now();
        LocalTime endTime = LocalTime.MIDNIGHT;
        if (currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(endTime)) {
            return true;
        }
        return false;
    }

    private UserRecord createOrGetUser(User user) {
        return userRecordRepository.findById(user.getId())
                .orElseGet(() -> {
                    UserRecord createdUser = new UserRecord(
                            user.getUsername(),
                            user.getPassword(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail()
                    );
                    return userRecordRepository.save(createdUser);
                });
    }

}
