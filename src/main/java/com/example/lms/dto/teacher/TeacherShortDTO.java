package com.example.lms.dto.teacher;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TeacherShortDTO { // для встраивания в CourseResponseDTO и ScheduleResponseDTO

    private UUID id;
    private String firstName;
    private String lastName;
}  