package com.example.lms.controller;

import com.example.lms.BaseIntegrationTest;
import com.example.lms.dto.group.GroupCreateDTO;
import com.example.lms.dto.group.GroupUpdateDTO;
import com.example.lms.dto.group.StudentsToGroupDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GroupControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @Sql("classpath:sql/create_group.sql")
    void createGroup_returns201() throws Exception {
        GroupCreateDTO dto = new GroupCreateDTO();
        dto.setName("Новая группа");

        mockMvc.perform(post("/api/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Новая группа"));
    }

    @Test
    @Sql("classpath:sql/groups_with_students.sql")
    void getGroupById_returns200() throws Exception {
        mockMvc.perform(get("/api/groups/{id}", "33333333-3333-3333-3333-333333333333"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Падаваны"))
                .andExpect(jsonPath("$.students.length()").value(2));
    }

    @Test
    void getGroupById_notFound_returns404() throws Exception {
        mockMvc.perform(get("/api/groups/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("badRequestGroup")
    void createGroup_badRequest_returns400(String name) throws Exception {
        GroupCreateDTO dto = new GroupCreateDTO();
        dto.setName(name);

        mockMvc.perform(post("/api/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    static Stream<Arguments> badRequestGroup() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("а".repeat(51))
        );
    }

    @Test
    @Sql("classpath:sql/groups.sql")
    void updateGroup_returns200() throws Exception {
        GroupUpdateDTO dto = new GroupUpdateDTO();
        dto.setName("Обновлённая группа");

        mockMvc.perform(put("/api/groups/{id}", "33333333-3333-3333-3333-333333333333")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Обновлённая группа"));
    }

    @Test
    @Sql("classpath:sql/groups.sql")
    void deleteGroup_returns204_andNotFoundAfter() throws Exception {
        mockMvc.perform(delete("/api/groups/{id}", "33333333-3333-3333-3333-333333333333"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/groups/{id}", "33333333-3333-3333-3333-333333333333"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql("classpath:sql/create_group.sql")
    void addStudentsToGroup_returns200() throws Exception {
        GroupCreateDTO groupDto = new GroupCreateDTO();
        groupDto.setName("Новая группа");
        String groupJson = mockMvc.perform(post("/api/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        String groupId = objectMapper.readTree(groupJson).get("id").asText();

        StudentsToGroupDTO dto = new StudentsToGroupDTO();
        dto.setStudentIds(List.of(UUID.fromString("44444444-4444-4444-4444-444444444444")));

        mockMvc.perform(post("/api/groups/{groupId}/students", groupId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.students.length()").value(1));
    }

    @Test
    @Sql("classpath:sql/groups_with_students.sql")
    void removeStudentsFromGroup_returns204() throws Exception {
        StudentsToGroupDTO dto = new StudentsToGroupDTO();
        dto.setStudentIds(List.of(UUID.fromString("44444444-4444-4444-4444-444444444444")));

        mockMvc.perform(delete("/api/groups/{groupId}/students", "33333333-3333-3333-3333-333333333333")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/groups/{id}", "33333333-3333-3333-3333-333333333333"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.students.length()").value(1));
    }
}