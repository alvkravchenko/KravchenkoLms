package com.example.lms.controller;

import com.example.lms.dto.CourseCreateDTO;
import com.example.lms.dto.CourseResponseDTO;
import com.example.lms.dto.CourseUpdateDTO;
import com.example.lms.dto.PageResponseDTO;
import com.example.lms.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDTO<CourseResponseDTO> getAll(Pageable pageable) {
        return null;
    } // пагинация

    @GetMapping("/{courseId}")
    public CourseResponseDTO getCourseById(@PathVariable UUID courseId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseCreateDTO createDTO) {
        return null;
    } // ResponseEntity для 201 + location

    @PutMapping("/{courseId}")
    public CourseResponseDTO updateCourse(@PathVariable UUID courseId, @Valid @RequestBody CourseUpdateDTO updateDTO) {
        return null;
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID courseId) {
        return null;
    }
} // ResponseEntity для 204 no content
