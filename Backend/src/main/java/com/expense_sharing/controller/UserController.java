package com.expense_sharing.controller;

import com.expense_sharing.dto.requests.user.CreateUserRequest;
import com.expense_sharing.dto.responses.UserResponse;
import com.expense_sharing.dto.responses.dtoConverter.DTOConverterFactory;
import com.expense_sharing.entity.User;
import com.expense_sharing.genericAPIResponse.APIResponse;
import com.expense_sharing.genericAPIResponse.ResponseUtil;
import com.expense_sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final DTOConverterFactory dtoConverterFactory;

    @PostMapping("/create-user")
    public ResponseEntity<APIResponse<UserResponse>> createUser(@RequestBody CreateUserRequest request){
        User user = userService.createUser(request);
        UserResponse userResponse = dtoConverterFactory.convertToDto(user);
        return ResponseUtil.success("User signed up successfully", userResponse);
    }

    @GetMapping("/{userId")
    public ResponseEntity<APIResponse<UserResponse>> getUserById(@PathVariable long userId){
        User user = userService.getUserById(userId);
        UserResponse userResponse = dtoConverterFactory.convertToDto(user);
        return ResponseUtil.success("User fetched successfully", userResponse);
    }

    @GetMapping("/{email}")
    public ResponseEntity<APIResponse<UserResponse>> getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        UserResponse userResponse = dtoConverterFactory.convertToDto(user);
        return ResponseUtil.success("User fetched successfully", userResponse);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<APIResponse<UserResponse>> getUserByPhoneNo(@PathVariable String phone){
        User user = userService.getUserByEmail(phone);
        UserResponse userResponse = dtoConverterFactory.convertToDto(user);
        return ResponseUtil.success("User fetched successfully", userResponse);
    }
}
