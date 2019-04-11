package io.codelex.loan.microlending.service;

import org.springframework.stereotype.Component;


@Component
public class InterestFactorService {
    
    Double extendLoanInterestFactor(Long amount, Long term){
        if(term == 7){
            
            return amount*Math.pow(1 + 0.015,7) - amount; 
        }
        if(term == 30){
            return (amount*Math.pow(1 + 0.015,7) - amount) * 4;
        }
        return null;
    }
    
}
