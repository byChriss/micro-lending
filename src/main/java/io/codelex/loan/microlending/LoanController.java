package io.codelex.loan.microlending;

import io.codelex.loan.microlending.api.Loan;
import io.codelex.loan.microlending.api.LoanRequest;

import io.codelex.loan.microlending.api.Status;
import io.codelex.loan.microlending.repository.model.ExtensionRecord;
import io.codelex.loan.microlending.repository.model.LoanRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {


    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping("/constraints")
    public ResponseEntity constraints(){
        return null;
    }

    @PostMapping("/loans/apply")
    public ResponseEntity<Loan> creatLoanRequest(Principal principal, @Valid @RequestBody LoanRequest request, HttpServletRequest httpRequest) {
        if(service.checkApplication(request,httpRequest, principal.getName()).getStatus() == Status.APPROVED){
            try {
                return new ResponseEntity<>(service.createLoan(principal.getName(), request), HttpStatus.OK);
            }catch (IllegalStateException e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }catch (InvalidParameterException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
      return null;
    }

    @PostMapping("/loans/{id}/extend")
    public ResponseEntity<Loan> extendLoanRequest(@PathVariable Long id, @RequestParam Long days) {
        return new ResponseEntity<>(service.findByIdAndExtend(id, days), HttpStatus.OK);
    }

    @GetMapping("/loans/{id}/extensions")
    public ResponseEntity<List<ExtensionRecord>> getExtensions(@PathVariable Long id) {
        return new ResponseEntity<>(service.getLoansWithExtensions(id), HttpStatus.OK);
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanRecord>> getActualLoansForUser(Principal principal) {
        return new ResponseEntity<>(service.findLoanByUserEmail(principal.getName()), HttpStatus.OK);
    }

}
