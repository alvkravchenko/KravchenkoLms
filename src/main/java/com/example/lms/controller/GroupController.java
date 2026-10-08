package com.example.lms.controller;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.group.*;
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

    @PostMapping("/{groupId}/students") // эндпоинт для добавления связи
    public GroupResponseDTO addStudentsToGroup(@PathVariable UUID groupId, @Valid @RequestBody StudentsToGroupDTO dto) {
        return null;
    }

    @DeleteMapping("/{groupId}/students") // эндпоинт для удаления связи
    public GroupResponseDTO removeStudentsFromGroup(@PathVariable UUID groupId, @Valid @RequestBody StudentsToGroupDTO dto) {
        return null;
    }
}