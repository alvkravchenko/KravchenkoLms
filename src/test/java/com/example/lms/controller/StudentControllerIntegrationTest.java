package com.example.lms.controller;

import com.example.lms.BaseIntegrationTest;
import com.example.lms.dto.student.StudentCreateDTO;
import com.example.lms.dto.student.StudentUpdateDTO;
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

class StudentControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @Sql("classpath:sql/create_students.sql")
    void createStudent_returns201() throws Exception {
        StudentCreateDTO dto = new StudentCreateDTO();
        dto.setFirstName("Пётр");
        dto.setLastName("Петров");
        dto.setGroupIds(List.of(UUID.fromString("33333333-3333-3333-3333-333333333333")));

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Пётр"))
                .andExpect(jsonPath("$.lastName").value("Петров"))
                .andExpect(jsonPath("$.groups[0].id").value("33333333-3333-3333-3333-333333333333"));
    }

    @Test
    @Sql("classpath:sql/students.sql")
    void getStudentById_returns200() throws Exception {
        mockMvc.perform(get("/api/students/{id}", "44444444-4444-4444-4444-444444444444"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Иван"))
                .andExpect(jsonPath("$.lastName").value("Иванов"))
                .andExpect(jsonPath("$.groups[0].id").value("33333333-3333-3333-3333-333333333333"));
    }

    @Test
    void getStudentById_notFound_returns404() throws Exception {
        mockMvc.perform(get("/api/students/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("badRequestStudent")
    void createStudent_badRequest_returns400(String firstName, String lastName, List<UUID> groupIds) throws Exception {
        StudentCreateDTO dto = new StudentCreateDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setGroupIds(groupIds);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    static Stream<Arguments> badRequestStudent() {
        return Stream.of(
                Arguments.of(null, "Иванов", List.of(UUID.randomUUID())),
                Arguments.of("", "Иванов", List.of(UUID.randomUUID())),
                Arguments.of("Иван", null, List.of(UUID.randomUUID())),
                Arguments.of("Иван", "", List.of(UUID.randomUUID())),
                Arguments.of("Иван", "Иванов", null),
                Arguments.of("Иван", "Иванов", List.of())
        );
    }

    @Test
    @Sql("classpath:sql/students.sql")
    void updateStudent_returns200() throws Exception {
        StudentUpdateDTO dto = new StudentUpdateDTO();
        dto.setFirstName("Сидор");
        dto.setLastName("Сидоров");

        mockMvc.perform(put("/api/students/{id}", "44444444-4444-4444-4444-444444444444")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Сидор"))
                .andExpect(jsonPath("$.lastName").value("Сидоров"));
    }

    @Test
    @Sql("classpath:sql/students.sql")
    void deleteStudent_returns204_andNotFoundAfter() throws Exception {
        mockMvc.perform(delete("/api/students/{id}", "44444444-4444-4444-4444-444444444444"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/students/{id}", "44444444-4444-4444-4444-444444444444"))
                .andExpect(status().isNotFound());
    }
}