package com.example.lms.service;

import com.example.lms.dto.StudentCreateDTO;
import com.example.lms.dto.StudentResponseDTO;
import com.example.lms.dto.StudentUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class StudentService {

    public Page<StudentResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    public StudentResponseDTO findById(UUID studentId) {
        return null;
    }

    public StudentResponseDTO create(StudentCreateDTO createDTO) {
        return null;
    }

    public StudentResponseDTO update(UUID studentId, StudentUpdateDTO updateDTO) {
        return null;
    }

    public void delete(UUID studentId) {

    }

    public StudentResponseDTO addStudentToGroup(UUID studentId, UUID groupId) {
        return null;
    }

    public StudentResponseDTO removeStudentFromGroup(UUID studentId, UUID groupId) {
        return null;
    }
}