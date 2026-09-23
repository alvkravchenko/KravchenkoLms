package com.example.lms.service;

import com.example.lms.dto.GroupCreateDTO;
import com.example.lms.dto.GroupResponseDTO;
import com.example.lms.dto.GroupUpdateDTO;
import com.example.lms.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GroupService {

    public PageResponseDTO<GroupResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    public GroupResponseDTO findById(UUID groupId) {
        return null;
    }

    public GroupResponseDTO create(GroupCreateDTO createDTO) {
        return null;
    }

    public GroupResponseDTO update(UUID groupId, GroupUpdateDTO updateDTO) {
        return null;
    }

    public void delete(UUID groupId) {

    }

    public GroupResponseDTO addStudentToGroup(UUID groupId, UUID studentId) {
        return null;
    }

    public GroupResponseDTO removeStudentFromGroup(UUID groupId, UUID studentId) {
        return null;
    }
}