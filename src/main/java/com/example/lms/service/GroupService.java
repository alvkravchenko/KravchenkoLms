package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.group.GroupCreateDTO;
import com.example.lms.dto.group.GroupResponseDTO;
import com.example.lms.dto.group.GroupUpdateDTO;
import com.example.lms.dto.group.StudentsToGroupDTO;
import com.example.lms.entity.Group;
import com.example.lms.entity.Student;
import com.example.lms.exception.ConflictException;
import com.example.lms.exception.StudentNotFoundException;
import com.example.lms.mapper.GroupMapper;
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
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final StudentRepository studentRepository;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public PageResponseDTO<GroupResponseDTO> findAll(Pageable pageable) {
        return PageResponseDTO.from(groupRepository.findAll(pageable).map(groupMapper::toResponseDTO));
    }

    @Transactional(readOnly = true)
    public GroupResponseDTO findById(UUID groupId) {
        Group group = groupRepository.findByIdOrThrow(groupId);
        return groupMapper.toResponseDTO(group);
    }

    @Transactional
    public GroupResponseDTO create(GroupCreateDTO createDTO) {
        if (groupRepository.findByName(createDTO.getName()).isPresent()) {
            throw new ConflictException("Группа с таким названием уже существует");
        }

        Group group = groupMapper.toEntity(createDTO);
        Group saved = groupRepository.save(group);
        return groupMapper.toResponseDTO(saved);
    }

    @Transactional
    public GroupResponseDTO update(UUID groupId, GroupUpdateDTO updateDTO) {
        Group group = groupRepository.findByIdOrThrow(groupId);

        if (groupRepository.findByName(updateDTO.getName())
                .filter(g -> !g.getId().equals(groupId))
                .isPresent()) {
            throw new ConflictException("Группа с таким названием уже существует");
        }

        groupMapper.applyFrom(group, updateDTO);
        Group saved = groupRepository.save(group);
        return groupMapper.toResponseDTO(saved);
    }

    @Transactional
    public void delete(UUID groupId) {
        Group group = groupRepository.findByIdOrThrow(groupId);
        group.setDeleted(true);
        group.setDeletedAt(LocalDateTime.now());
        groupRepository.save(group);
    }

    @Transactional
    public GroupResponseDTO addStudentsToGroup(UUID groupId, StudentsToGroupDTO dto) {
        Group group = groupRepository.findByIdOrThrow(groupId);
        List<Student> students = studentRepository.findAllById(dto.getStudentIds());
        if (students.size() != dto.getStudentIds().size()) {
            throw new StudentNotFoundException("Не все переданные студенты найдены");
        }
        group.getStudents().addAll(students);
        return groupMapper.toResponseDTO(group);
    }

    @Transactional
    public GroupResponseDTO removeStudentsFromGroup(UUID groupId, StudentsToGroupDTO dto) {
        Group group = groupRepository.findByIdOrThrow(groupId);
        group.getStudents().removeIf(s -> dto.getStudentIds().contains(s.getId()));
        return groupMapper.toResponseDTO(group);
    }
}