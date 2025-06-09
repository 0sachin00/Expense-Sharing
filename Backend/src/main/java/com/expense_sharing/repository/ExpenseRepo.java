package com.expense_sharing.repository;

import com.expense_sharing.entity.Expense;
import com.expense_sharing.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByGroup(Group groupId);
}
