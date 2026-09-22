package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentUpdateDTO {

    @NotBlank(message = "Имя студента не может быть пустым")
    @Size(max = 50, message = "Имя не может быть длиннее 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия студента не может быть пустым")
    @Size(max = 50, message = "Фамилия не может быть длиннее 50 символов")
    private String lastName;
}