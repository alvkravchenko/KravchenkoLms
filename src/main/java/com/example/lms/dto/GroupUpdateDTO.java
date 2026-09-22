package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupUpdateDTO {

    @NotBlank(message = "Название группы не может быть пустым")
    @Size(max = 50, message = "Название группы не может быть длиннее 50 символов")
    private String name;
}