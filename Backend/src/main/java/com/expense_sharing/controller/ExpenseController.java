package com.expense_sharing.controller;

import com.expense_sharing.dto.requests.expense.CreateExpenseRequest;
import com.expense_sharing.dto.requests.expense.UpdateExpenseRequest;
import com.expense_sharing.dto.responses.ExpenseResponse;
import com.expense_sharing.dto.responses.dtoConverter.DTOConverterFactory;
import com.expense_sharing.entity.Expense;
import com.expense_sharing.genericAPIResponse.APIResponse;
import com.expense_sharing.genericAPIResponse.ResponseUtil;
import com.expense_sharing.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final DTOConverterFactory dtoConverterFactory;

    @PostMapping("/create-expense")
    public ResponseEntity<APIResponse<ExpenseResponse>> createExpense(@RequestBody CreateExpenseRequest request){
        Expense expense = expenseService.createExpense(request);
        ExpenseResponse expenseResponse = dtoConverterFactory.convertToDto(expense);
        return ResponseUtil.success("Expense added successfully", expenseResponse);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<APIResponse<ExpenseResponse>> getExpenseById(@PathVariable long expenseId){
        Expense expense = expenseService.getExpenseById(expenseId);
        ExpenseResponse expenseResponse = dtoConverterFactory.convertToDto(expense);
        return ResponseUtil.success("Expenses fetched successfully", expenseResponse);
    }

    @GetMapping("/groups/{groupId}/expenses")
    public ResponseEntity<APIResponse<List<ExpenseResponse>>> getExpensesByGroup(@PathVariable long groupId){
        List<Expense> expenses = expenseService.getExpensesByGroup(groupId);
        List<ExpenseResponse> expenseResponses = dtoConverterFactory.convertToDtoList(expenses);
        return ResponseUtil.success("Expenses fetched successfully", expenseResponses);
    }

    @PutMapping("/update-expenses/{expenseId}")
    public ResponseEntity<APIResponse<ExpenseResponse>> updateExpense(@PathVariable long expenseId, @RequestBody UpdateExpenseRequest request) {
        Expense expense = expenseService.updateExpense(expenseId, request);
        ExpenseResponse expenseResponse = dtoConverterFactory.convertToDto(expense);
        return ResponseUtil.success("Expense updated successfully", expenseResponse);
    }

}
