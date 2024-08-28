package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.entity.Account;
import com.entity.Loan;
import com.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    
    @Autowired
  	RestTemplate restTemplate;
//
//    @PostMapping("/apply")
//    public ResponseEntity<Loan> applyForLoan(@RequestBody Loan loan) {
//        Loan appliedLoan = loanService.applyForLoan(loan);
//        return new ResponseEntity<>(appliedLoan, HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Loan loan = loanService.getLoanById(id);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long id) {
        Loan approvedLoan = loanService.approveLoan(id);
        if (approvedLoan != null) {
            return new ResponseEntity<>(approvedLoan, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/deny")
    public ResponseEntity<Loan> denyLoan(@PathVariable Long id) {
        Loan deniedLoan = loanService.denyLoan(id);
        if (deniedLoan != null) {
            return new ResponseEntity<>(deniedLoan, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
//        loanService.deleteLoan(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    
    @GetMapping("/allloans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        if (!loans.isEmpty()) {
            return new ResponseEntity<>(loans, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   
}

