package com.example.lms.mapper;

import com.example.lms.dto.TeacherCreateDTO;
import com.example.lms.dto.TeacherResponseDTO;
import com.example.lms.dto.TeacherShortDTO;
import com.example.lms.dto.TeacherUpdateDTO;
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
