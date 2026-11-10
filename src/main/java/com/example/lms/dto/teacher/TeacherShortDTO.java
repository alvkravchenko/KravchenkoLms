package com.example.lms.dto.teacher;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TeacherShortDTO {

    private UUID id;
    private String firstName;
    private String lastName;
}  