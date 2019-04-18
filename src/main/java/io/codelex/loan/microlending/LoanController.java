package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanExtension;
import io.codelex.loan.microlending.api.LoanRequest;

import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoanController {


    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }


    @PostMapping("/loans")
    public ResponseEntity<Loan> creatLoanRequest(Principal principal, @Valid @RequestBody LoanRequest request, HttpServletRequest httpRequest) {
        return new ResponseEntity<>(service.createLoan(principal.getName(), request, httpRequest), HttpStatus.ACCEPTED);
    }

    @PostMapping("/loans/{id}/extend")
    public ResponseEntity<Loan> extendLoanRequest(@PathVariable Long id, @RequestParam Long days) {
        return new ResponseEntity<>(service.findByIdAndExtend(id, days), HttpStatus.OK);
    }
    @GetMapping("loans/extensions")
    public ResponseEntity<Map<LoanRecord, List<ExtensionRecord>>> getExtensions(Principal principal){
        return new ResponseEntity<>(service.getLoansWithExtensions(principal.getName()), HttpStatus.FOUND);
    }

}
