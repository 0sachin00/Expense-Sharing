package com.expense_sharing.service;

import com.expense_sharing.dto.requests.user.CreateUserRequest;
import com.expense_sharing.entity.Balance;
import com.expense_sharing.entity.User;
import com.expense_sharing.repository.BalanceRepo;
import com.expense_sharing.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final BalanceRepo balanceRepo;

    public User createUser(CreateUserRequest request){
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userRepo.existsByPhoneNo(request.getPhoneNo())) {
            throw new IllegalArgumentException("Phone number already in use");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNo(request.getPhoneNo())
                .groups(List.of())
                .expensesPaid(List.of())
                .shares(List.of())
                .transactionsSent(List.of())
                .transactionsReceived(List.of())
                .build();

        User savedUser = userRepo.save(user);

        Balance balance = new Balance();
        balance.setUser(savedUser);
        balance.setNetBalance(BigDecimal.ZERO);

        balanceRepo.save(balance);

        savedUser.setBalance(balance);
        return savedUser;
    }

    public User getUserById(long userId){
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found with Email: " + email));
    }


    public User getUserByPhoneNo(String phoneNo){
        return userRepo.findByPhoneNo(phoneNo)
                .orElseThrow(() -> new RuntimeException("User not found with Phone No: " + phoneNo));
    }
}
