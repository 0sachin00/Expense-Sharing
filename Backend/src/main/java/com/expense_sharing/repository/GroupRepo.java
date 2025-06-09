package com.expense_sharing.repository;

import com.expense_sharing.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Long> {
}
