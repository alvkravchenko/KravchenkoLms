package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseUpdateDTO {

    @NotBlank(message = "Название курса не может быть пустым")
    private String name;

    @NotBlank(message = "Описание курса не может быть пустым")
    private String description;
}
