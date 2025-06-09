package com.expense_sharing.repository;

import com.expense_sharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNo(String phoneNo);

    Optional<User> findByEmailId(String email);

    Optional<User> findByPhoneNo(String phoneNo);
}
