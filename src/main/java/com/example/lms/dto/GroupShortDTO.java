package com.example.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GroupShortDTO { // для встраивания в StudentResponseDTO и ScheduleResponseDTO

    private UUID id;
    private String name;
}