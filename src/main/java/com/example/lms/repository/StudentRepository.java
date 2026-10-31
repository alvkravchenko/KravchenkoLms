package com.example.lms.repository;

import com.example.lms.entity.Student;
import com.example.lms.exception.StudentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    default Student findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new StudentNotFoundException("Студент с id " + id + " не найден"));
    }
}
