package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudentShortDTO { // для встраивания в GroupResponseDTO

    private UUID id;
    private String firstName;
    private String lastName;
}