package com.expense_sharing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    CREDIT("Credit"),
    DEBIT("Debit"),
    FAILED("Failed");

    private final String type;
}
