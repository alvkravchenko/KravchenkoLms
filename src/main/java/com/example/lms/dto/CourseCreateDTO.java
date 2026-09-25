package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseCreateDTO {

    @NotBlank(message = "Название курса не может быть пустым")
    @Size(max = 50, message = "Название курса не может быть длиннее 50 символов")
    private String name;

    @NotBlank(message = "Описание курса не может быть пустым")
    @Size(max = 1000, message = "Описание курса не может быть длиннее 1000 символов")
    private String description;
}