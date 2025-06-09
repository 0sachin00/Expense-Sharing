package com.expense_sharing.repository;

import com.expense_sharing.entity.Transaction;
import com.expense_sharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromUserOrToUser(User fromUser, User toUser);
}
