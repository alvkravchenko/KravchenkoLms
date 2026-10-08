package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.group.GroupCreateDTO;
import com.example.lms.dto.group.GroupResponseDTO;
import com.example.lms.dto.group.GroupUpdateDTO;
import com.example.lms.dto.group.StudentsToGroupDTO;
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

    public GroupResponseDTO addStudentsToGroup(UUID groupId, StudentsToGroupDTO dto) {
        return null;
    }

    public GroupResponseDTO removeStudentsFromGroup(UUID groupId, StudentsToGroupDTO dto) {
        return null;
    }
}