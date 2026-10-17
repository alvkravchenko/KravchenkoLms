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

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public PageResponseDTO<StudentResponseDTO> findAll(Pageable pageable) {
        return PageResponseDTO.from(studentRepository.findAll(pageable).map(studentMapper::toResponseDTO));
    }

    public StudentResponseDTO findById(UUID studentId) {

        Student student = studentRepository.findByIdOrThrow(studentId);
        return studentMapper.toResponseDTO(student);

    }

    public StudentResponseDTO create(StudentCreateDTO createDTO) {
        List<Group> groupList = groupRepository.findAllById(createDTO.getGroupIds());
        if (groupList.size() != createDTO.getGroupIds().size()) {
            throw new GroupNotFoundException("Не все переданные группы найдены");
        }
        Student student = studentMapper.toEntity(createDTO);
        student.setGroups(new HashSet<>(groupList));
        Student saved = studentRepository.save(student);
        return studentMapper.toResponseDTO(saved);
    }

    public StudentResponseDTO update(UUID studentId, StudentUpdateDTO updateDTO) {
        Student student = studentRepository.findByIdOrThrow(studentId);
        studentMapper.applyFrom(student, updateDTO);
    }

    public void delete(UUID studentId) {
    }
}