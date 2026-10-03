package com.example.lms.controller;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.student.StudentCreateDTO;
import com.example.lms.dto.student.StudentResponseDTO;
import com.example.lms.dto.student.StudentUpdateDTO;
import com.example.lms.service.StudentService;
import jakarta.validation.Valid;
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
    public PageResponseDTO<StudentResponseDTO> getAll(Pageable pageable) {
        return null;
    }// пагинация

    @GetMapping("/{studentId}")
    public StudentResponseDTO getStudentById(@PathVariable UUID studentId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentCreateDTO createDTO) {
        return null;
    } // ResponseEntity для 201 + location

    @PutMapping("/{studentId}")
    public StudentResponseDTO updateStudent(@PathVariable UUID studentId, @Valid @RequestBody StudentUpdateDTO updateDTO) {
        return null;
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID studentId) {
        return null;
    }
    // ResponseEntity для 204 no content
}