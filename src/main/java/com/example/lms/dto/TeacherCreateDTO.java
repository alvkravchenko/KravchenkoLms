package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherCreateDTO {

    @NotBlank(message = "Имя преподавателя не может быть пустым")
    @Size(max = 50, message = "Имя преподавателя не может быть длиннее 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия преподавателя не может быть пустой")
    @Size(max = 50, message = "Фамилия преподавателя не может быть длиннее 50 символов")
    private String lastName;
}