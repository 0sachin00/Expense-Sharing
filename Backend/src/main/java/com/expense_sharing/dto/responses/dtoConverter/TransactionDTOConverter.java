package com.expense_sharing.dto.responses.dtoConverter;

import com.expense_sharing.dto.responses.TransactionResponse;
import com.expense_sharing.entity.Transaction;

public class TransactionDTOConverter implements DTOConverter<Transaction, TransactionResponse>{

    @Override
    public TransactionResponse convert(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .from(transaction.getFromUser().getName())
                .to(transaction.getToUser().getName())
                .amount(transaction.getAmount())
                .build();
    }
}
