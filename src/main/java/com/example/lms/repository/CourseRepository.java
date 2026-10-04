package com.example.lms.repository;

import com.example.lms.entity.Course;
import com.example.lms.exception.CourseNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    default Course findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new CourseNotFoundException("Курс с id " + id + " не найден"));
    }
}
