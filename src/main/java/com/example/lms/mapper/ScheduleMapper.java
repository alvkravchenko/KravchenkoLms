package com.example.lms.mapper;

import com.example.lms.dto.schedule.ScheduleCreateDTO;
import com.example.lms.dto.schedule.ScheduleResponseDTO;
import com.example.lms.dto.schedule.ScheduleUpdateDTO;
import com.example.lms.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, TeacherMapper.class, CourseMapper.class})
public interface ScheduleMapper {

    ScheduleResponseDTO toResponseDTO(Schedule schedule);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "course", ignore = true)
    Schedule toEntity(ScheduleCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateEntity(@MappingTarget Schedule schedule, ScheduleUpdateDTO updateDTO);
}