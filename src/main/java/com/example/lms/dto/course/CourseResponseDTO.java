package com.example.lms.dto.course;

import com.example.lms.dto.teacher.TeacherShortDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CourseResponseDTO {

    private UUID id;
    private String name;
    private String description;
    private TeacherShortDTO teacher; // в ответе по курсу есть инфа про препода
}