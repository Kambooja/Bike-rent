package com.sha.serverbikemanagement.repository;

import com.sha.serverbikemanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
