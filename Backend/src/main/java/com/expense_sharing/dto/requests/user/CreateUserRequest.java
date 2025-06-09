package com.expense_sharing.dto.requests.user;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String phoneNo;
}
