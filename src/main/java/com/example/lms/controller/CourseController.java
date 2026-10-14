package com.example.lms.controller;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.course.CourseCreateDTO;
import com.example.lms.dto.course.CourseResponseDTO;
import com.example.lms.dto.course.CourseUpdateDTO;
import com.example.lms.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public PageResponseDTO<CourseResponseDTO> getAll(Pageable pageable) {
        return courseService.findAll(pageable);
    } // пагинация

    @GetMapping("/{courseId}")
    public CourseResponseDTO getCourseById(@PathVariable UUID courseId) {
        return courseService.findById(courseId);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseCreateDTO createDTO) {
        CourseResponseDTO created = courseService.create(createDTO);
        URI location = URI.create("/api/courses/" + created.getId());
        return ResponseEntity.created(location).body(created);
    } // ResponseEntity для 201 + location

    @PutMapping("/{courseId}")
    public CourseResponseDTO updateCourse(@PathVariable UUID courseId, @Valid @RequestBody CourseUpdateDTO updateDTO) {
        return courseService.update(courseId, updateDTO);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID courseId) {
        courseService.delete(courseId);
        return ResponseEntity.noContent().build();
    }// ResponseEntity для 204 no content

    @PutMapping("/{courseId}/teacher/{teacherId}")
    public CourseResponseDTO changeTeacher (@PathVariable UUID courseId, @PathVariable UUID teacherId){
        return null;
    }
}