package com.expense_sharing.dto.requests.group;

import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequest {
    private String name;
    private long creatorId;
    private List<Long> adminIds;
    private List<Long> memberIds;
}
