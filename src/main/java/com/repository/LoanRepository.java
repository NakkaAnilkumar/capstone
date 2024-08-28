package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    // You can add custom queries here if needed
}
