package com.expense_sharing.service;

import com.expense_sharing.TransactionType;
import com.expense_sharing.dto.responses.TransactionResponse;
import com.expense_sharing.entity.Transaction;
import com.expense_sharing.entity.User;
import com.expense_sharing.repository.TransactionRepo;
import com.expense_sharing.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    public List<TransactionResponse> getAllTransactionsByUserId(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID:" + userId));

        List<Transaction> transactions = transactionRepo.findByFromUserOrToUser(user, user);

        return buildTransactionResponses(transactions, userId);
    }

    private List<TransactionResponse> buildTransactionResponses(List<Transaction> transactions, long userId) {
        return transactions.stream().map(tx ->{
                TransactionType type;
                String counterPart;

                if (tx.getFromUser().getId() == userId) {
                    type = TransactionType.DEBIT;
                    counterPart = tx.getToUser().getName();
                } else {
                    type = TransactionType.CREDIT;
                    counterPart = tx.getFromUser().getName();
                }
                return new TransactionResponse(tx.getId(), type.getType(), counterPart, tx.getAmount());
            }
        ).toList();
    }
}
