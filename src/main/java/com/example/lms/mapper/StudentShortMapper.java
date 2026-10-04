package com.example.lms.mapper;

import com.example.lms.dto.student.StudentShortDTO;
import com.example.lms.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentShortMapper {

    StudentShortDTO toShort(Student student);
}