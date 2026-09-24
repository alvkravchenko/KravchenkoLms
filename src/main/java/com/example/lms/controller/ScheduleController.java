package com.example.lms.controller;

import com.example.lms.dto.ScheduleCreateDTO;
import com.example.lms.dto.ScheduleResponseDTO;
import com.example.lms.dto.ScheduleUpdateDTO;
import com.example.lms.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<ScheduleResponseDTO> getAll() {
        return null;
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDTO getScheduleById(@PathVariable UUID scheduleId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@Valid @RequestBody ScheduleCreateDTO createDTO) {
        return null;
    }

    @PatchMapping("/{scheduleId}")
    public ScheduleResponseDTO updateSchedule(@PathVariable UUID scheduleId, @Valid @RequestBody ScheduleUpdateDTO updateDTO) {
        return null;
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable UUID scheduleId) {
        return null;
    }

    @GetMapping("/by-group/{groupId}")
    public List<ScheduleResponseDTO> getGroupSchedule(@PathVariable UUID groupId) {
        return null;
    }

    @GetMapping("/by-teacher/{teacherId}")
    public List<ScheduleResponseDTO> getTeacherSchedule(@PathVariable UUID teacherId) {
        return null;
    }
}