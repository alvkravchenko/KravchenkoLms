package com.example.lms.dto.group;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudentsToGroupDTO {

    @NotEmpty(message = "Список должен содержать минимум 1 студента")
    private List<UUID> studentIds;
}