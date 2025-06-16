package com.expense_sharing.dto.responses.dtoConverter;

import com.expense_sharing.dto.responses.GroupResponse;
import com.expense_sharing.dto.responses.UserResponse;
import com.expense_sharing.entity.Group;

public class GroupDTOConverter implements DTOConverter<Group, GroupResponse> {

    @Override
    public GroupResponse convert(Group entity) {
        return GroupResponse.builder()
                .groupId(entity.getId())
                .name(entity.getName())
                .users(entity.getUsers().stream().map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNo(user.getPhoneNo())
                        .balance(user.getBalance().getNetBalance())
                        .build()).toList())
                .admins(entity.getAdmins().stream().map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNo(user.getPhoneNo())
                        .balance(user.getBalance().getNetBalance())
                        .build()).toList())
                .build();
    }
}
