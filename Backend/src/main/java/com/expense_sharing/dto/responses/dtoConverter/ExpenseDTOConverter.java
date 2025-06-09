package com.expense_sharing.dto.responses.dtoConverter;

import com.expense_sharing.dto.responses.ExpenseResponse;
import com.expense_sharing.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseDTOConverter implements DTOConverter<Expense, ExpenseResponse> {

    @Override
    public ExpenseResponse convert(Expense expense) {
        return ExpenseResponse.builder()
                .amount(expense.getAmount())
                .paidBy(expense.getPaidBy())
                .group(expense.getGroup())
                .build();
    }
}
