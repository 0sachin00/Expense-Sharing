package com.expense_sharing.genericAPIResponse;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<APIResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(new APIResponse<>(200, message, data));
    }
}
