package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TeacherShortDTO { // на будущее, для включение в курс и расписание,
    // чтобы не отдавать туда все поля препода

    private UUID id; // нет валидации, это исходящий DTO (Response)
    private String firstName;
    private String lastName;
}