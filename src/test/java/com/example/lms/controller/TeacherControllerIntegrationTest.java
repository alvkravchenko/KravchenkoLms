package com.example.lms.controller;

import com.example.lms.BaseIntegrationTest;
import com.example.lms.dto.teacher.TeacherCreateDTO;
import com.example.lms.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeacherControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    void setUp() {
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
    void getTeacherById_returns200() throws Exception {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("Пётр");
        dto.setLastName("Петров");

        String responseJson = mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();
        String id = objectMapper.readTree(responseJson).get("id").asText();

        mockMvc.perform(get("/api/teachers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Пётр"));
    }

    @Test
    void getTeacherById_notFound_returns404() throws Exception {

        mockMvc.perform(get("/api/teachers/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void createTeacher_emptyFirstName_returns400() throws Exception {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("");
        dto.setLastName("Иванов");

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteTeacher_returns204_andNotFoundAfter() throws Exception {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("Сидор");
        dto.setLastName("Сидоров");

        String responseJson = mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();

        String id = objectMapper.readTree(responseJson).get("id").asText();

        mockMvc.perform(delete("/api/teachers/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/teachers/{id}", id))
                .andExpect(status().isNotFound());
    }
}