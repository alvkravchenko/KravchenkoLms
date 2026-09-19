package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudentCreateDTO {

    @NotBlank(message = "Имя студента не может быть пустым")
    @Size(max = 50, message = "Имя не может быть длиннее 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия студента не может быть пустым")
    @Size(max = 50, message = "Фамилия не может быть длиннее 50 символов")
    private String lastName;

    @NotEmpty(message = "Студент должен быть хотя бы в одной группе")
    private List<UUID> groupIds;
}
