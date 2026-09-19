package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GroupCreateDTO {

    @NotBlank(message = "Название группы не может быть пустым")
    @Size(max = 50, message = "Название группы не может быть длиннее 50 символов")
    private String name;

    private List<UUID> studentIds;
}