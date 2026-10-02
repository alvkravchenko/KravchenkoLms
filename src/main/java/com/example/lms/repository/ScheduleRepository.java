package com.example.lms.repository;

import com.example.lms.entity.Schedule;
import com.example.lms.exception.ScheduleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    default Schedule findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new ScheduleNotFoundException("Расписание с id " + id + " не найдено"));
    }
}
