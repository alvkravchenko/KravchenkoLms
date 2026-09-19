package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CourseShortDTO {

    private UUID id;
    private String name;
}