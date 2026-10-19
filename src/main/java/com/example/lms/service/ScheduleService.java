package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.schedule.ScheduleCreateDTO;
import com.example.lms.dto.schedule.ScheduleResponseDTO;
import com.example.lms.dto.schedule.ScheduleUpdateDTO;
import com.example.lms.entity.Course;
import com.example.lms.entity.Group;
import com.example.lms.entity.Schedule;
import com.example.lms.mapper.ScheduleMapper;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.GroupRepository;
import com.example.lms.repository.ScheduleRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ScheduleMapper scheduleMapper,
                           GroupRepository groupRepository,
                           CourseRepository courseRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    public PageResponseDTO<ScheduleResponseDTO> findAll(Pageable pageable) {
        return PageResponseDTO.from(scheduleRepository.findAll(pageable).map(scheduleMapper::toResponseDTO));
    }

    public ScheduleResponseDTO findById(UUID scheduleId) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(scheduleId);
        return scheduleMapper.toResponseDTO(schedule);
    }

    public ScheduleResponseDTO create(ScheduleCreateDTO createDTO) {
        Group group = groupRepository.findByIdOrThrow(createDTO.getGroupId());
        Course course = courseRepository.findByIdOrThrow(createDTO.getCourseId());

        Schedule schedule = scheduleMapper.toEntity(createDTO);
        schedule.setGroup(group);
        schedule.setCourse(course);
        schedule.setTeacher(course.getTeacher());

        Schedule saved = scheduleRepository.save(schedule);
        return scheduleMapper.toResponseDTO(saved);
    }

    public ScheduleResponseDTO update(UUID scheduleId, ScheduleUpdateDTO updateDTO) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(scheduleId);

        Group group = groupRepository.findByIdOrThrow(updateDTO.getGroupId());
        Course course = courseRepository.findByIdOrThrow(updateDTO.getCourseId());

        scheduleMapper.applyFrom(schedule, updateDTO);
        schedule.setGroup(group);
        schedule.setCourse(course);
        schedule.setTeacher(course.getTeacher());

        Schedule saved = scheduleRepository.save(schedule);
        return scheduleMapper.toResponseDTO(saved);
    }

    public void delete(UUID scheduleId) {
        Schedule schedule = scheduleRepository.findByIdOrThrow(scheduleId);
        scheduleRepository.delete(schedule);
    }
}