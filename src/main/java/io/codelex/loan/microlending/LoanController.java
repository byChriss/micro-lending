package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoanController {
    
    private LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }
    
    @PostMapping
    private ResponseEntity<Loan> getLoanRequest(@RequestBody LoanRequest request){
        return null;
    }
}
