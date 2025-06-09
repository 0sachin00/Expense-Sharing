package com.expense_sharing.service;

import com.expense_sharing.entity.Group;
import com.expense_sharing.repository.GroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepo groupRepo;

    public Group getGroupDetails(long groupId){
        return groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with ID: " + groupId));
    }
}
