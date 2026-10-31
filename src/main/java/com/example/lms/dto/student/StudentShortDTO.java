package com.example.lms.dto.student;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudentShortDTO {

    private UUID id;
    private String firstName;
    private String lastName;
}