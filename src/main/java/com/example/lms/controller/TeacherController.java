package com.example.lms.controller;

import com.example.lms.dto.TeacherCreateDTO;
import com.example.lms.dto.TeacherResponseDTO;
import com.example.lms.dto.TeacherUpdateDTO;
import com.example.lms.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public Page<TeacherResponseDTO> getAll(Pageable pageable) {
        return null;
    } // пагинация

    @GetMapping("/{teacherId}")
    public TeacherResponseDTO getTeacherById(@PathVariable UUID teacherId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@Valid @RequestBody TeacherCreateDTO createDTO) {
        return null;
    } // ResponseEntity для 201 + location

    @PutMapping("/{teacherId}")
    public TeacherResponseDTO updateTeacher(@PathVariable UUID teacherId, @Valid @RequestBody TeacherUpdateDTO updateDTO) {
        return null;
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID teacherId) {
        return null;
    }
} // ResponseEntity для 204 no content
