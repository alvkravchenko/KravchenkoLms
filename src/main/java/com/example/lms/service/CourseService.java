package com.example.lms.service;

import com.example.lms.dto.CourseCreateDTO;
import com.example.lms.dto.CourseResponseDTO;
import com.example.lms.dto.CourseUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {

    public Page<CourseResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    public CourseResponseDTO findById(UUID courseId) {
        return null;
    }

    public CourseResponseDTO create(CourseCreateDTO createDTO) {
        return null;
    }

    public CourseResponseDTO update(UUID courseId, CourseUpdateDTO updateDTO) {
        return null;
    }

    public void delete(UUID courseId) {

    }
}