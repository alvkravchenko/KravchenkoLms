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

import java.net.URI;
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
        return studentService.findAll(pageable);
    }// пагинация

    @GetMapping("/{studentId}")
    public StudentResponseDTO getStudentById(@PathVariable UUID studentId) {
        return studentService.findById(studentId);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentCreateDTO createDTO) {
        StudentResponseDTO created = studentService.create(createDTO);
        URI location = URI.create("/api/students/" + created.getId());
        return ResponseEntity.created(location).body(created);
    } // ResponseEntity для 201 + location

    @PutMapping("/{studentId}")
    public StudentResponseDTO updateStudent(@PathVariable UUID studentId, @Valid @RequestBody StudentUpdateDTO updateDTO) {
        return studentService.update(studentId, updateDTO);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID studentId) {
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }
    // ResponseEntity для 204 no content
}