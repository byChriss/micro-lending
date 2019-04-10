package io.codelex.loan.microlending.inMemory.service;

import io.codelex.loan.microlending.api.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanService {
    private List<Loan> loans = new ArrayList<>();

    public LoanService() {
    }

    public Loan addLoan(Loan loan) {
        loans.add(loan);
        return loan;
    }

    public Optional<Loan> getLoan(Long id) {
        return loans.stream()
                .filter(loan -> loan.getId().equals(id))
                .findFirst();

    }
}
