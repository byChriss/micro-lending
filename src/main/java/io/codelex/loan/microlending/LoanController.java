package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;
import io.codelex.loan.microlending.service.LoanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class LoanController {

    private LoanService service;

    @PutMapping("/loans")
     public ResponseEntity<Loan> creatLoanRequest(@RequestBody LoanRequest request, HttpServletRequest httpRequest){
        return new ResponseEntity<>(service.createLoan(request, httpRequest), HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/loans/{id}/extend")
    public ResponseEntity<Loan> extendLoanRequest(@PathVariable Long id, @RequestParam Long days){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
