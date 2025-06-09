package com.expense_sharing.controller;

import com.expense_sharing.dto.responses.ExpenseResponse;
import com.expense_sharing.dto.responses.TransactionResponse;
import com.expense_sharing.dto.responses.dtoConverter.DTOConverterFactory;
import com.expense_sharing.genericAPIResponse.APIResponse;
import com.expense_sharing.genericAPIResponse.ResponseUtil;
import com.expense_sharing.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final DTOConverterFactory dtoConverterFactory;

    @GetMapping("/{userId}/all-transactions")
    public ResponseEntity<APIResponse<List<TransactionResponse>>> getAllTransactionsByUserId(@PathVariable long userId){
        List<TransactionResponse> transactionResponses = transactionService.getAllTransactionsByUserId(userId);
        return ResponseUtil.success("All transactions fetched successfully", transactionResponses);
    }
}
