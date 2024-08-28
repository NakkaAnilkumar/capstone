package com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Loan;
import com.repository.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public Loan applyForLoan(Loan loan) {
        loan.setApprovalDate(null);
        loan.setApproved(false);
        return loanRepository.save(loan);
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Transactional
    public Loan approveLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        if (loan != null) {
            loan.setApproved(true);
            loan.setApprovalDate(LocalDateTime.now());
            return loanRepository.save(loan);
        }
        return null;
    }

    @Transactional
    public Loan denyLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        if (loan != null) {
            loan.setApproved(false);
            loan.setApprovalDate(null);
            return loanRepository.save(loan);
        }
        return null;
    }

    @Transactional
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
