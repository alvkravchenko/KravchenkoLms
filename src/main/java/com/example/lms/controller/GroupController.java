package com.example.lms.controller;

import com.example.lms.dto.GroupCreateDTO;
import com.example.lms.dto.GroupResponseDTO;
import com.example.lms.dto.GroupUpdateDTO;
import com.example.lms.dto.PageResponseDTO;
import com.example.lms.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    public PageResponseDTO<GroupResponseDTO> getAll(Pageable pageable) {
        return null;
    }

    @GetMapping("/{groupId}")
    public GroupResponseDTO getGroupById(@PathVariable UUID groupId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<GroupResponseDTO> createGroup(@Valid @RequestBody GroupCreateDTO createDTO) {
        return null;
    }// ResponseEntity для 201 + location

    @PutMapping("/{groupId}")
    public GroupResponseDTO updateGroup(@PathVariable UUID groupId, @Valid @RequestBody GroupUpdateDTO updateDTO) {
        return null;
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID groupId) {
        return null;
    } // ResponseEntity для 204 no content

    @PostMapping("/{groupId}/students/{studentId}") // эндпоинт для добавления связи
    public GroupResponseDTO addStudentToGroup(@PathVariable UUID groupId, @PathVariable UUID studentId) {
        return null;
    }

    @DeleteMapping("/{groupId}/students/{studentId}") // эндпоинт для удаления связи
    public GroupResponseDTO removeStudentFromGroup(@PathVariable UUID groupId, @PathVariable UUID studentId) {
        return null;
    }
}