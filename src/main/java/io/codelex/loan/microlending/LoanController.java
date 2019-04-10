package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.inMemory.InMemoryLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoanController {


    private InMemoryLoanService service;

    
    @PostMapping("/loan")
    public ResponseEntity<Loan> getLoanRequest(@RequestBody LoanRequest request){
        return new ResponseEntity<>(service.createLoan(request), HttpStatus.ACCEPTED);


    }
}
