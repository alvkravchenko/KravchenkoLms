package com.example.lms.controller;

import com.example.lms.dto.StudentCreateDTO;
import com.example.lms.dto.StudentResponseDTO;
import com.example.lms.dto.StudentUpdateDTO;
import com.example.lms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Page<StudentResponseDTO> getAll(Pageable pageable) {
        return null;
    }

    @GetMapping("/{studentId}")
    public StudentResponseDTO getStudentById(@PathVariable UUID studentId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentCreateDTO createDTO) {
        return null;
    }

    @PutMapping("/{studentId}")
    public StudentResponseDTO updateStudent(@PathVariable UUID studentId, @Valid @RequestBody StudentUpdateDTO updateDTO) {
        return null;
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID studentId) {
        return null;
    }

    @PostMapping("/{studentId}/groups/{groupId}")
    public StudentResponseDTO addStudentToGroup(@PathVariable UUID studentId, @PathVariable UUID groupId) {
        return null;
    }

    @DeleteMapping("/{studentId}/groups/{groupId}")
    public StudentResponseDTO removeStudentFromGroup(@PathVariable UUID studentId, @PathVariable UUID groupId) {
        return null;
    }
}