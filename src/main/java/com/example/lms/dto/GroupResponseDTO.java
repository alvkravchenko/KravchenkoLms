package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GroupResponseDTO {

    private UUID id;
    private String name;
    private List<StudentShortDTO> students; // в ответе по группе есть инфа о студентах группы
}