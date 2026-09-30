package com.example.lms.mapper;

import com.example.lms.dto.StudentCreateDTO;
import com.example.lms.dto.StudentResponseDTO;
import com.example.lms.dto.StudentUpdateDTO;
import com.example.lms.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = GroupShortMapper.class)
public interface StudentMapper {

    StudentResponseDTO toResponseDTO(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groups", ignore = true)
    Student toEntity(StudentCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groups", ignore = true)
    void updateEntity(@MappingTarget Student student, StudentUpdateDTO updateDTO);
}