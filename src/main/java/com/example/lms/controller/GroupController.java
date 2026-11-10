package com.example.lms.controller;

import com.example.lms.dto.PageResponseDTO;
import com.example.lms.dto.group.GroupCreateDTO;
import com.example.lms.dto.group.GroupResponseDTO;
import com.example.lms.dto.group.GroupUpdateDTO;
import com.example.lms.dto.group.StudentsToGroupDTO;
import com.example.lms.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        return service.findAll(pageable);
    }

    @GetMapping("/{groupId}")
    public GroupResponseDTO getGroupById(@PathVariable UUID groupId) {
        return service.findById(groupId);
    }

    @PostMapping
    public ResponseEntity<GroupResponseDTO> createGroup(@Valid @RequestBody GroupCreateDTO createDTO) {
        GroupResponseDTO created = service.create(createDTO);
        URI location = URI.create("/api/groups/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{groupId}")
    public GroupResponseDTO updateGroup(@PathVariable UUID groupId, @Valid @RequestBody GroupUpdateDTO updateDTO) {
        return service.update(groupId, updateDTO);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable UUID groupId) {
        service.delete(groupId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{groupId}/students") // эндпоинт для добавления связи
    public ResponseEntity<GroupResponseDTO> addStudentsToGroup(@PathVariable UUID groupId, @Valid @RequestBody StudentsToGroupDTO dto) {
        return ResponseEntity.ok(service.addStudentsToGroup(groupId, dto));
    }

    @DeleteMapping("/{groupId}/students") // эндпоинт для удаления связи
    public ResponseEntity<Void> removeStudentsFromGroup(@PathVariable UUID groupId, @Valid @RequestBody StudentsToGroupDTO dto) {
        service.removeStudentsFromGroup(groupId, dto);
        return ResponseEntity.noContent().build();
    }
}