package com.example.lms.service;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.course.CourseCreateDTO;
import com.example.lms.dto.course.CourseResponseDTO;
import com.example.lms.dto.course.CourseUpdateDTO;
import com.example.lms.entity.Course;
import com.example.lms.entity.Teacher;
import com.example.lms.exception.ConflictException;
import com.example.lms.mapper.CourseMapper;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.TeacherRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.teacherRepository = teacherRepository;
    }

    public PageResponseDTO<CourseResponseDTO> findAll(Pageable pageable) {
        return PageResponseDTO.from(courseRepository.findAll(pageable).map(courseMapper::toResponseDTO));
    }

    public CourseResponseDTO findById(UUID courseId) {
        Course course = courseRepository.findByIdOrThrow(courseId);
        return courseMapper.toResponseDTO(course);
    }

    public CourseResponseDTO create(CourseCreateDTO createDTO) {
        if (courseRepository.findByName(createDTO.getName()).isPresent()) {
            throw new ConflictException("Курс с таким названием уже существует");
        }
        if (courseRepository.findByDescription(createDTO.getDescription()).isPresent()) {
            throw new ConflictException("Курс с таким описанием уже существует");
        }
        Teacher teacher = teacherRepository.findByIdOrThrow(createDTO.getTeacherId());
        Course course = courseMapper.toEntity(createDTO);
        course.setTeacher(teacher);
        Course saved = courseRepository.save(course);
        return courseMapper.toResponseDTO(saved);
    }

    public CourseResponseDTO update(UUID courseId, CourseUpdateDTO updateDTO) {

        Course course = courseRepository.findByIdOrThrow(courseId);

        if (courseRepository.findByName(updateDTO.getName())
                .filter(c -> !c.getId().equals(courseId))
                .isPresent()) {
            throw new ConflictException("Курс с таким названием уже существует");
        }
        if (courseRepository.findByDescription(updateDTO.getDescription())
                .filter(c -> !c.getId().equals(courseId))
                .isPresent()) {
            throw new ConflictException("Курс с таким описанием уже существует");
        }

        courseMapper.applyFrom(course, updateDTO);
        Course saved = courseRepository.save(course);
        return courseMapper.toResponseDTO(saved);
    }

    public void delete(UUID courseId) {
        Course course = courseRepository.findByIdOrThrow(courseId);
        course.setDeleted(true);
        course.setDeletedAt(LocalDateTime.now());
        courseRepository.save(course);
    }

    public CourseResponseDTO changeTeacher(UUID courseId, UUID teacherId) {

        Course course = courseRepository.findByIdOrThrow(courseId);
        Teacher teacher = teacherRepository.findByIdOrThrow(teacherId);

        if (course.getTeacher().getId().equals(teacherId)) {
            return courseMapper.toResponseDTO(course);  // ничего не меняем
        }

        course.setTeacher(teacher);
        Course saved = courseRepository.save(course);
        return courseMapper.toResponseDTO(saved);
    }
}