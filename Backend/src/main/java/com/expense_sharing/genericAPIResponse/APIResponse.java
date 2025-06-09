package com.expense_sharing.genericAPIResponse;

public record APIResponse<T>(int status, String message, T data) {
}
