package com.expense_sharing.service;

import com.expense_sharing.dto.requests.expense.CreateExpenseRequest;
import com.expense_sharing.dto.requests.expense.UpdateExpenseRequest;
import com.expense_sharing.entity.Expense;
import com.expense_sharing.entity.Group;
import com.expense_sharing.entity.Share;
import com.expense_sharing.entity.User;
import com.expense_sharing.repository.ExpenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final GroupService groupService;
    private final UserService userService;

    public Expense createExpense(CreateExpenseRequest expenseRequest) {
        Group group = groupService.getGroupDetails(expenseRequest.getGroupId());

        List<User> users = new ArrayList<>();
        if(group != null){
            users = group.getUsers();
        }

        User paidBy = userService.getUserById(expenseRequest.getPaidBy());
        BigDecimal shareAmount = expenseRequest.getAmount().divide(BigDecimal.valueOf(users.size()), 2, RoundingMode.HALF_UP);

        Expense expense = Expense.builder()
                .desc(expenseRequest.getDesc())
                .amount(expenseRequest.getAmount())
                .paidBy(paidBy)
                .group(group)
                .build();

        List<Share> shares = users.stream()
                .map(user -> Share.builder()
                        .expense(expense)
                        .user(user)
                        .amount(user.equals(paidBy) ? BigDecimal.ZERO : shareAmount)
                        .build())
                .toList();

        expense.setShares(shares);

        return expenseRepo.save(expense);
    }

    public Expense getExpenseById(long expenseId) {
        return expenseRepo.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + expenseId));
    }

    public List<Expense> getExpensesByGroup(long groupId) {
        Group group = groupService.getGroupDetails(groupId);

        if (group == null) return null;
        return expenseRepo.findByGroup(group);
    }

    public Expense updateExpense(long expenseId, UpdateExpenseRequest expenseRequest) {
        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + expenseId));

        List<User> users = expense.getGroup().getUsers();
        User paidBy = userService.getUserById(expenseRequest.getPaidBy());

        expense.setDesc(expenseRequest.getDesc());
        expense.setAmount(expenseRequest.getAmount());
        expense.setPaidBy(paidBy);

        BigDecimal shareAmount = expenseRequest.getAmount().divide(BigDecimal.valueOf(users.size()), 2, RoundingMode.HALF_UP);
        expense.getShares().clear();

        List<Share> shares = users.stream()
                .map(user -> Share.builder()
                        .expense(expense)
                        .user(user)
                        .amount(user.equals(paidBy) ? BigDecimal.ZERO : shareAmount)
                        .build())
                .toList();

        expense.getShares().addAll(shares);

        return expenseRepo.save(expense);
    }
}
