package com.expense_sharing.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    private long groupId;
    private String name;
    private List<UserResponse> users;
    private List<UserResponse> admins;
}
