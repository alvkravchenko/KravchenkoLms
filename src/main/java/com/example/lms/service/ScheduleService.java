package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.schedule.ScheduleCreateDTO;
import com.example.lms.dto.schedule.ScheduleResponseDTO;
import com.example.lms.dto.schedule.ScheduleUpdateDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleService {

    public PageResponseDTO<ScheduleResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    public ScheduleResponseDTO findById(UUID scheduleId) {
        return null;
    }

    public ScheduleResponseDTO create(ScheduleCreateDTO createDTO) {
        return null;
    }

    public ScheduleResponseDTO update(UUID scheduleId, ScheduleUpdateDTO updateDTO) {
        return null;
    }

    public void delete(UUID scheduleId) {

    }
}