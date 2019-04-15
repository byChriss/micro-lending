package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class LoanController {

    
    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }


    @PostMapping("/loans")
     public ResponseEntity<Loan> creatLoanRequest(Principal principal, @RequestBody LoanRequest request, HttpServletRequest httpRequest){
        return new ResponseEntity<>(service.createLoan(principal.getName(), request, httpRequest), HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/loans/{id}/extend")
    public ResponseEntity<Loan> extendLoanRequest(@PathVariable Long id, @RequestParam Long days){
        return new ResponseEntity<>(service.findByIdAndExtend(id, days), HttpStatus.OK);
    }
}
