package com.example.lms.mapper;

import com.example.lms.dto.student.StudentCreateDTO;
import com.example.lms.dto.student.StudentResponseDTO;
import com.example.lms.dto.student.StudentUpdateDTO;
import com.example.lms.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = GroupShortMapper.class)
public interface StudentMapper {

    StudentResponseDTO toResponseDTO(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Student toEntity(StudentCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void applyFrom(@MappingTarget Student student, StudentUpdateDTO updateDTO);
}