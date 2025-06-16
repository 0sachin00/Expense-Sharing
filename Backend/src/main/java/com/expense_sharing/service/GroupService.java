package com.expense_sharing.service;

import com.expense_sharing.dto.requests.group.CreateGroupRequest;
import com.expense_sharing.entity.Group;
import com.expense_sharing.entity.User;
import com.expense_sharing.repository.GroupRepo;
import com.expense_sharing.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepo groupRepo;
    private final UserRepo userRepo;

    public Group getGroupDetails(long groupId){
        return groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with ID: " + groupId));
    }

    public Group createGroup(CreateGroupRequest request) {
        request.getAdminIds().add(request.getCreatorId());
        Group group = Group.builder()
                .name(request.getName())
                .members(request.getMemberIds().stream().map(id -> {
                        if(userRepo.findById(id).isPresent()){
                            return userRepo.findById(id).get();
                        }
                        return null;
                    }).toList())
                .admins(request.getAdminIds().stream().map(id -> {
                        if(userRepo.findById(id).isPresent()){
                            return userRepo.findById(id).get();
                        }
                        return null;
                    }).toList())
                .build();
        return groupRepo.save(group);
    }
}
