package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.teacher.TeacherCreateDTO;
import com.example.lms.dto.teacher.TeacherResponseDTO;
import com.example.lms.dto.teacher.TeacherUpdateDTO;
import com.example.lms.entity.Teacher;
import com.example.lms.mapper.TeacherMapper;
import com.example.lms.repository.TeacherRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public PageResponseDTO<TeacherResponseDTO> findAll(Pageable pageable) {
        return PageResponseDTO.from(teacherRepository.findAll(pageable).map(teacherMapper::toResponseDTO));
    }

    public TeacherResponseDTO findById(UUID teacherId) {
        Teacher teacher = teacherRepository.findByIdOrThrow(teacherId);
        return teacherMapper.toResponseDTO(teacher);
    }

    public TeacherResponseDTO create(TeacherCreateDTO createDTO) {
        Teacher teacher = teacherMapper.toEntity(createDTO);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toResponseDTO(saved);
    }

    public TeacherResponseDTO update(UUID teacherId, TeacherUpdateDTO updateDTO) {
        Teacher teacher = teacherRepository.findByIdOrThrow(teacherId);
        teacherMapper.applyFrom(teacher, updateDTO);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toResponseDTO(saved);
    }

    public void delete(UUID teacherId) {
        Teacher teacher = teacherRepository.findByIdOrThrow(teacherId);
        teacher.setDeleted(true);
        teacher.setDeletedAt(LocalDateTime.now());
        teacherRepository.save(teacher);
    }
}