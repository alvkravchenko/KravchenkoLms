package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherUpdateDTO {

    @NotBlank(message = "Имя преподавателя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия преподавателя не может быть пустой")
    private String lastName;
}