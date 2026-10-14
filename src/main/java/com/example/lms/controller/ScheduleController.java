package com.example.lms.controller;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.schedule.ScheduleCreateDTO;
import com.example.lms.dto.schedule.ScheduleResponseDTO;
import com.example.lms.dto.schedule.ScheduleUpdateDTO;
import com.example.lms.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
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
    public PageResponseDTO<ScheduleResponseDTO> getAll(Pageable pageable) {
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

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDTO updateSchedule(@PathVariable UUID scheduleId, @Valid @RequestBody ScheduleUpdateDTO updateDTO) {
        return null;
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable UUID scheduleId) {
        return null;
    }
}