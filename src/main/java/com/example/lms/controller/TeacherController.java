package com.example.lms.controller;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.teacher.TeacherCreateDTO;
import com.example.lms.dto.teacher.TeacherResponseDTO;
import com.example.lms.dto.teacher.TeacherUpdateDTO;
import com.example.lms.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public PageResponseDTO<TeacherResponseDTO> getAll(Pageable pageable) {
        return teacherService.findAll(pageable);
    }

    @GetMapping("/{teacherId}")
    public TeacherResponseDTO getTeacherById(@PathVariable UUID teacherId) {
        return teacherService.findById(teacherId);
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@Valid @RequestBody TeacherCreateDTO createDTO) {
        TeacherResponseDTO created = teacherService.create(createDTO);
        URI location = URI.create("/api/teachers/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{teacherId}")
    public TeacherResponseDTO updateTeacher(@PathVariable UUID teacherId, @Valid @RequestBody TeacherUpdateDTO updateDTO) {
        return teacherService.update(teacherId, updateDTO);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID teacherId) {
        teacherService.delete(teacherId);
        return ResponseEntity.noContent().build();
    }
}