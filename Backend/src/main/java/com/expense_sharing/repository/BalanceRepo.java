package com.expense_sharing.repository;

import com.expense_sharing.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepo extends JpaRepository<Balance, Long> {
}
