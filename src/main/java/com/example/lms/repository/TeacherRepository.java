package com.example.lms.repository;

import com.example.lms.entity.Teacher;
import com.example.lms.exception.TeacherNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    default Teacher findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new TeacherNotFoundException("Учитель с id " + id + " не найден"));
    }
}
