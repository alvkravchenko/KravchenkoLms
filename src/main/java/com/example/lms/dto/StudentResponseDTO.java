package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudentResponseDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private List<GroupShortDTO> groups; // в ответе по студенту есть инфа по его группам
}