package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.student.StudentCreateDTO;
import com.example.lms.dto.student.StudentResponseDTO;
import com.example.lms.dto.student.StudentUpdateDTO;
import com.example.lms.entity.Group;
import com.example.lms.entity.Student;
import com.example.lms.exception.GroupNotFoundException;
import com.example.lms.mapper.StudentMapper;
import com.example.lms.repository.GroupRepository;
import com.example.lms.repository.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional(readOnly = true)
    public PageResponseDTO<StudentResponseDTO> findAll(Pageable pageable) {
        return PageResponseDTO.from(studentRepository.findAll(pageable).map(studentMapper::toResponseDTO));
    }

    @Transactional(readOnly = true)
    public StudentResponseDTO findById(UUID studentId) {

        Student student = studentRepository.findByIdOrThrow(studentId);
        return studentMapper.toResponseDTO(student);

    }

    @Transactional
    public StudentResponseDTO create(StudentCreateDTO createDTO) {
        List<Group> groupList = groupRepository.findAllById(createDTO.getGroupIds());
        if (groupList.size() != createDTO.getGroupIds().size()) {
            throw new GroupNotFoundException("Не все переданные группы найдены");
        }
        Student student = studentMapper.toEntity(createDTO);
        Student saved = studentRepository.save(student);
        for (Group group : groupList) {
            group.getStudents().add(saved);
            saved.getGroups().add(group);
        }
        return studentMapper.toResponseDTO(saved);
    }

    @Transactional
    public StudentResponseDTO update(UUID studentId, StudentUpdateDTO updateDTO) {
        Student student = studentRepository.findByIdOrThrow(studentId);
        studentMapper.applyFrom(student, updateDTO);
        Student saved = studentRepository.save(student);
        return studentMapper.toResponseDTO(saved);
    }

    @Transactional
    public void delete(UUID studentId) {
        Student student = studentRepository.findByIdOrThrow(studentId);
        student.setDeleted(true);
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
    }
}