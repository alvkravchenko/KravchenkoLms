package com.example.lms.dto.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ScheduleFilterDTO {
    private UUID groupId;
    private UUID teacherId;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;
}
