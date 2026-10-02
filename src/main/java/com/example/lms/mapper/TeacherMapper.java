package com.example.lms.mapper;

import com.example.lms.dto.teacher.TeacherCreateDTO;
import com.example.lms.dto.teacher.TeacherResponseDTO;
import com.example.lms.dto.teacher.TeacherShortDTO;
import com.example.lms.dto.teacher.TeacherUpdateDTO;
import com.example.lms.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherResponseDTO toResponseDTO(Teacher teacher);

    TeacherShortDTO toShort(Teacher teacher);

    @Mapping(target = "id", ignore = true) // сейчас id нет в DTO, но полезно на будущее
    Teacher toEntity(TeacherCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Teacher teacher, TeacherUpdateDTO updateDTO);
}
