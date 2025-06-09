package com.expense_sharing.dto.responses;

import com.expense_sharing.entity.Group;
import com.expense_sharing.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponse {
    private BigDecimal amount;
    private User paidBy;
    private Group group;
}
