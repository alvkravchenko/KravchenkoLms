package com.example.lms.controller;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.filter.ScheduleFilterDTO;
import com.example.lms.dto.schedule.ScheduleCreateDTO;
import com.example.lms.dto.schedule.ScheduleResponseDTO;
import com.example.lms.dto.schedule.ScheduleUpdateDTO;
import com.example.lms.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDTO<ScheduleResponseDTO> getAll(Pageable pageable, ScheduleFilterDTO filterDTO) {
        return service.findAll(pageable, filterDTO);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDTO getScheduleById(@PathVariable UUID scheduleId) {
        return service.findById(scheduleId);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@Valid @RequestBody ScheduleCreateDTO createDTO) {
        ScheduleResponseDTO created = service.create(createDTO);
        URI location = URI.create("/api/schedules/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDTO updateSchedule(@PathVariable UUID scheduleId, @Valid @RequestBody ScheduleUpdateDTO updateDTO) {
        return service.update(scheduleId, updateDTO);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable UUID scheduleId) {
        service.delete(scheduleId);
        return ResponseEntity.noContent().build();
    }
}