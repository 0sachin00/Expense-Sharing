package com.expense_sharing.dto.responses.dtoConverter;

import com.expense_sharing.dto.responses.UserResponse;
import com.expense_sharing.entity.User;

public class UserDTOConverter implements DTOConverter<User, UserResponse>{

    @Override
    public UserResponse convert(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNo(user.getPhoneNo())
                .balance(user.getBalance().getNetBalance())
                .build();
    }
}
