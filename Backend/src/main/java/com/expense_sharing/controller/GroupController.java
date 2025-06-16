package com.expense_sharing.controller;

import com.expense_sharing.dto.requests.group.CreateGroupRequest;
import com.expense_sharing.dto.responses.GroupResponse;
import com.expense_sharing.dto.responses.dtoConverter.DTOConverterFactory;
import com.expense_sharing.entity.Group;
import com.expense_sharing.genericAPIResponse.APIResponse;
import com.expense_sharing.genericAPIResponse.ResponseUtil;
import com.expense_sharing.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final DTOConverterFactory dtoConverterFactory;

    @PostMapping("create-group")
    public ResponseEntity<APIResponse<GroupResponse>> createGroup(@RequestBody CreateGroupRequest request){
        Group group = groupService.createGroup(request);
        GroupResponse groupResponse = dtoConverterFactory.convertToDto(user);
        return ResponseUtil.success("User signed up successfully", groupResponse);
    }
}
