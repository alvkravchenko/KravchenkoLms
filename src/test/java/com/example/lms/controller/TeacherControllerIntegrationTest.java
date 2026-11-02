package com.example.lms.controller;

import com.example.lms.BaseIntegrationTest;
import com.example.lms.dto.teacher.TeacherCreateDTO;
import com.example.lms.repository.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeacherControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

    @Test
    void createTeacher_returns201() throws Exception {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("Иван");
        dto.setLastName("Иванов");

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Иван"))
                .andExpect(jsonPath("$.lastName").value("Иванов"));
    }

    @Test
    @Sql("classpath:sql/teachers.sql")
    void getTeacherById_returns200() throws Exception {
        mockMvc.perform(get("/api/teachers/{id}", "a7eabc45-3794-47ab-a5c1-2f91268eb503"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Пётр"))
                .andExpect(jsonPath("$.lastName").value("Петров"));
    }

    @Test
    void getTeacherById_notFound_returns404() throws Exception {

        mockMvc.perform(get("/api/teachers/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("badRequestTeacher")
    void createTeacher_badrequest_returns400(String firstName, String lastName) throws Exception {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

    }

    static Stream<Arguments> badRequestTeacher() {
        return Stream.of(
                Arguments.of(null, "Иванов"),
                Arguments.of("", "Иванов"),
                Arguments.of(" ", "Иванов"),
                Arguments.of("а".repeat(51), "Иванов"),
                Arguments.of("Иван", null),
                Arguments.of("Иван", ""),
                Arguments.of("Иван", " "),
                Arguments.of("Иван", "а".repeat(51))
        );
    }

    @Test
    @Sql("classpath:sql/teachers.sql")
    void deleteTeacher_returns204_andNotFoundAfter() throws Exception {
        mockMvc.perform(delete("/api/teachers/{id}", "a7eabc45-3794-47ab-a5c1-2f91268eb503"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/teachers/{id}", "a7eabc45-3794-47ab-a5c1-2f91268eb503"))
                .andExpect(status().isNotFound());
    }
}