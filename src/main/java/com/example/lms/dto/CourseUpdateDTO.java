package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CourseUpdateDTO {

    @NotBlank(message = "Название курса не может быть пустым")
    @Size(max = 50, message = "Название курса не может быть длиннее 50 символов")
    private String name;

    @NotBlank(message = "Описание курса не может быть пустым")
    @Size(max = 1000, message = "Описание курса не может быть длиннее 1000 символов")
    private String description;

    @NotNull(message = "Курс не может быть обновлен без преподавателя")
    private UUID teacherId;
}
