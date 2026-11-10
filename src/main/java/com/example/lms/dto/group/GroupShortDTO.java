package com.example.lms.dto.group;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GroupShortDTO {

    private UUID id;
    private String name;
}