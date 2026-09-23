package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.TeacherCreateDTO;
import com.example.lms.dto.TeacherResponseDTO;
import com.example.lms.dto.TeacherUpdateDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TeacherService {

    public PageResponseDTO<TeacherResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    public TeacherResponseDTO findById(UUID teacherId) {
        return null;
    }

    public TeacherResponseDTO create(TeacherCreateDTO createDTO) {
        return null;
    }

    public TeacherResponseDTO update(UUID teacherId, TeacherUpdateDTO updateDTO) {
        return null;
    }

    public void delete(UUID teacherId) {

    }
}