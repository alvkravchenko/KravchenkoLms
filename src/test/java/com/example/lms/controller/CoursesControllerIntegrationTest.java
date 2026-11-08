package com.example.lms.controller;

import com.example.lms.BaseIntegrationTest;
import com.example.lms.dto.course.CourseCreateDTO;
import com.example.lms.dto.course.CourseUpdateDTO;
import com.example.lms.dto.teacher.TeacherCreateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CoursesControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @Sql("classpath:sql/courses.sql")
    void createCourse_returns201() throws Exception {
        CourseCreateDTO dto = new CourseCreateDTO();
        dto.setName("JavaMiniCourse");
        dto.setDescription("Java for dummies");
        dto.setTeacherId(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("JavaMiniCourse"))
                .andExpect(jsonPath("$.description").value("Java for dummies"))
                .andExpect(jsonPath("$.teacher.id").value("11111111-1111-1111-1111-111111111111"));
    }

    @Test
    @Sql("classpath:sql/courses.sql")
    void getCourseById_returns200() throws Exception {
        mockMvc.perform(get("/api/courses/{id}", "22222222-2222-2222-2222-222222222222"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Java"))
                .andExpect(jsonPath("$.description").value("Java course"))
                .andExpect(jsonPath("$.teacher.id").value("11111111-1111-1111-1111-111111111111"));
    }

    @Test
    void getCourseById_notFound_returns404() throws Exception {

        mockMvc.perform(get("/api/courses/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("badRequestCourse")
    void createCourse_badRequest_returns400(String name, String description) throws Exception {
        CourseCreateDTO dto = new CourseCreateDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setTeacherId(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    static Stream<Arguments> badRequestCourse() {
        return Stream.of(
                Arguments.of(null, "Java course"),
                Arguments.of("", "Java course"),
                Arguments.of(" ", "Java course"),
                Arguments.of("а".repeat(101), "Java course"),
                Arguments.of("Java", null),
                Arguments.of("Java", ""),
                Arguments.of("Java", " "),
                Arguments.of("Java", "а".repeat(1001))
        );
    }

    @Test
    @Sql("classpath:sql/courses.sql")
    void updateCourse_returns200() throws Exception {
        CourseUpdateDTO dto = new CourseUpdateDTO();
        dto.setName("Java Advanced");
        dto.setDescription("Advanced Java");

        mockMvc.perform(put("/api/courses/{id}", "22222222-2222-2222-2222-222222222222")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Java Advanced"))
                .andExpect(jsonPath("$.description").value("Advanced Java"));
    }

    @Test
    @Sql("classpath:sql/courses.sql")
    void deleteCourse_returns204_andNotFoundAfter() throws Exception {
        mockMvc.perform(delete("/api/courses/{id}", "22222222-2222-2222-2222-222222222222"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/courses/{id}", "22222222-2222-2222-2222-222222222222"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql("classpath:sql/courses.sql")
    void changeTeacher_returns200() throws Exception {
        TeacherCreateDTO newTeacher = new TeacherCreateDTO();
        newTeacher.setFirstName("Иван");
        newTeacher.setLastName("Иванов");

        String responseJson = mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTeacher)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        String newTeacherId = objectMapper.readTree(responseJson).get("id").asText();

        mockMvc.perform(put("/api/courses/{courseId}/teacher/{teacherId}",
                        "22222222-2222-2222-2222-222222222222", newTeacherId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teacher.id").value(newTeacherId));
    }
}