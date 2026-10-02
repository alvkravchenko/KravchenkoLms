package com.example.lms.dto.schedule;

import com.example.lms.dto.course.CourseShortDTO;
import com.example.lms.dto.group.GroupShortDTO;
import com.example.lms.dto.teacher.TeacherShortDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ScheduleResponseDTO {

    private UUID id;
    private GroupShortDTO group;
    private TeacherShortDTO teacher;
    private CourseShortDTO course;
    private LocalDate startDate;
    private LocalDate endDate;
}