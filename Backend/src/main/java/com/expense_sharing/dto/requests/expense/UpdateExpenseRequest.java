package com.expense_sharing.dto.requests.expense;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateExpenseRequest {
    private String desc;
    private BigDecimal amount;
    private long paidBy;
}
