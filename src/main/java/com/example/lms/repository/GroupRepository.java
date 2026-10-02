package com.example.lms.repository;

import com.example.lms.entity.Group;
import com.example.lms.exception.GroupNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {

    default Group findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new GroupNotFoundException("Группа с id " + id + " не найдена"));
    }
}
