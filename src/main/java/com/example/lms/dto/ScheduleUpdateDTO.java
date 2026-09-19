package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ScheduleUpdateDTO {

    private UUID groupId;
    private UUID teacherId;
    private UUID courseId;
    private LocalDate startDate;
    private LocalDate endDate;
}