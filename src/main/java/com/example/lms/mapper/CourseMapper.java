package com.example.lms.mapper;

import com.example.lms.dto.CourseCreateDTO;
import com.example.lms.dto.CourseResponseDTO;
import com.example.lms.dto.CourseShortDTO;
import com.example.lms.dto.CourseUpdateDTO;
import com.example.lms.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = TeacherMapper.class)
public interface CourseMapper {

    CourseResponseDTO toResponseDTO(Course course);

    CourseShortDTO toShort(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    Course toEntity(CourseCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    void updateEntity(@MappingTarget Course course, CourseUpdateDTO updateDTO);
}