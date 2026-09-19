package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CourseResponseDTO {

    private UUID id;
    private String name;
    private String description;
    private TeacherShortDTO teacher;
}