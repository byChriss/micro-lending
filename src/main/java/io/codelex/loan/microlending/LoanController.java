package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.*;

import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
public class LoanController {


    private final LoanService service;


    public LoanController(LoanService service) {
        this.service = service;

    }

    @GetMapping("/constraints")
    public ResponseEntity<Constraints> constraints() {
        Constraints constraints = service.setConstraints();
        return new ResponseEntity<>(constraints, HttpStatus.OK);
    }

    @PostMapping("/loans/apply")
    public ResponseEntity<ApplicationResponse> creatLoanRequest(Principal principal, @Valid @RequestBody LoanRequest request, HttpServletRequest httpRequest) {
        if (!service.amountValidation(request) || !service.termValidation(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Application application = service.application(principal, request, httpRequest);
            ApplicationResponse response = new ApplicationResponse(application.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/loans/{id}/extend")
    public ResponseEntity<Loan> extendLoanRequest(Principal principal, @PathVariable String id, @RequestParam Integer days) {
        try {
            return new ResponseEntity<>(service.findByIdAndExtend(principal, id, days), HttpStatus.OK);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/loans/{id}/extensions")
    public ResponseEntity<List<ExtensionRecord>> getExtensions(@PathVariable Long id) {
        return new ResponseEntity<>(service.findLoansWithExtensions(id), HttpStatus.OK);
    }

    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getActualLoansForUser(Principal principal) {
        return new ResponseEntity<>(service.findLoan(principal.getName()), HttpStatus.OK);
    }
}
