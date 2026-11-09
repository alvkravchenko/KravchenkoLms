package com.example.lms.controller;

import com.example.lms.BaseIntegrationTest;
import com.example.lms.dto.schedule.ScheduleCreateDTO;
import com.example.lms.dto.schedule.ScheduleUpdateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScheduleControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @Sql("classpath:sql/create_schedule.sql")
    void createSchedule_returns201() throws Exception {
        ScheduleCreateDTO dto = new ScheduleCreateDTO();
        dto.setGroupId(UUID.fromString("33333333-3333-3333-3333-333333333333"));
        dto.setCourseId(UUID.fromString("22222222-2222-2222-2222-222222222222"));
        dto.setStartDate(LocalDate.now().plusDays(1));
        dto.setEndDate(LocalDate.now().plusMonths(3));

        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.group.id").value("33333333-3333-3333-3333-333333333333"))
                .andExpect(jsonPath("$.course.id").value("22222222-2222-2222-2222-222222222222"))
                .andExpect(jsonPath("$.teacher.id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$.startDate").value(LocalDate.now().plusDays(1).toString()))
                .andExpect(jsonPath("$.endDate").value(LocalDate.now().plusMonths(3).toString()));
    }

    @Test
    @Sql("classpath:sql/schedules.sql")
    void getScheduleById_returns200() throws Exception {
        mockMvc.perform(get("/api/schedules/{id}", "66666666-6666-6666-6666-666666666666"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.group.id").value("33333333-3333-3333-3333-333333333333"))
                .andExpect(jsonPath("$.teacher.id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$.course.id").value("22222222-2222-2222-2222-222222222222"))
                .andExpect(jsonPath("$.startDate").value("2026-01-01"))
                .andExpect(jsonPath("$.endDate").value("2026-06-01"));
    }

    @Test
    void getScheduleById_notFound_returns404() throws Exception {
        mockMvc.perform(get("/api/schedules/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("badRequestSchedule")
    void createSchedule_badRequest_returns400(
            UUID groupId, UUID courseId, LocalDate startDate, LocalDate endDate) throws Exception {
        ScheduleCreateDTO dto = new ScheduleCreateDTO();
        dto.setGroupId(groupId);
        dto.setCourseId(courseId);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);

        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    static Stream<Arguments> badRequestSchedule() {
        UUID groupId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        UUID courseId = UUID.fromString("22222222-2222-2222-2222-222222222222");
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2026, 6, 1);

        return Stream.of(
                Arguments.of(null, courseId, start, end),
                Arguments.of(groupId, null, start, end),
                Arguments.of(groupId, courseId, null, end),
                Arguments.of(groupId, courseId, start, null),
                Arguments.of(groupId, courseId, LocalDate.now().minusDays(1), end)   // start в прошлом
        );
    }

    @Test
    @Sql("classpath:sql/schedules.sql")
    void updateSchedule_returns200() throws Exception {
        ScheduleUpdateDTO dto = new ScheduleUpdateDTO();
        dto.setGroupId(UUID.fromString("33333333-3333-3333-3333-333333333333"));
        dto.setCourseId(UUID.fromString("22222222-2222-2222-2222-222222222222"));
        dto.setStartDate(LocalDate.now().plusDays(2));
        dto.setEndDate(LocalDate.now().plusMonths(4));

        mockMvc.perform(put("/api/schedules/{id}", "66666666-6666-6666-6666-666666666666")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate").value(LocalDate.now().plusDays(2).toString()))
                .andExpect(jsonPath("$.endDate").value(LocalDate.now().plusMonths(4).toString()));
    }

    @Test
    @Sql("classpath:sql/schedules.sql")
    void deleteSchedule_returns204_andNotFoundAfter() throws Exception {
        mockMvc.perform(delete("/api/schedules/{id}", "66666666-6666-6666-6666-666666666666"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/schedules/{id}", "66666666-6666-6666-6666-666666666666"))
                .andExpect(status().isNotFound());
    }
}