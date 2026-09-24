package com.example.lms.service;

import com.example.lms.dto.ScheduleCreateDTO;
import com.example.lms.dto.ScheduleResponseDTO;
import com.example.lms.dto.ScheduleUpdateDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {

    public List<ScheduleResponseDTO> findAll() {
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

    public List<ScheduleResponseDTO> getGroupSchedule(UUID groupId) {
        return null;
    }

    public List<ScheduleResponseDTO> getTeacherSchedule(UUID teacherId) {
        return null;
    }
}