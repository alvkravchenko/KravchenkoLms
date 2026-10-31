package com.example.lms.dto.schedule;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ScheduleUpdateDTO {

    @NotNull(message = "Расписание не может быть обновлено без группы")
    private UUID groupId;

    @NotNull(message = "Расписание не может быть обновлено без курса")
    private UUID courseId;

    @NotNull(message = "Дата начала занятий не может быть пустой")
    @FutureOrPresent(message = "Дата начала занятий не может быть в прошлом")
    private LocalDate startDate;

    @NotNull(message = "Дата завершения занятий не может быть пустой")
    @FutureOrPresent(message = "Дата завершения занятий не может быть в прошлом")
    private LocalDate endDate;
}