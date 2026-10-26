package com.example.lms.controller;

import com.example.lms.dto.teacher.TeacherCreateDTO;
import com.example.lms.dto.teacher.TeacherResponseDTO;
import com.example.lms.repository.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.resttestclient.TestRestTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestRestTemplate
class TeacherControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TeacherRepository teacherRepository;

    private String baseUrl() {
        return "http://localhost:" + port + "/api/teachers";
    }

    @BeforeEach
    void setUp() {
        teacherRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

    @Test
    void createTeacher_returns201() {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("Иван");
        dto.setLastName("Иванов");

        ResponseEntity<TeacherResponseDTO> response =
                restTemplate.postForEntity(baseUrl(), dto, TeacherResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("Иван");
        assertThat(response.getBody().getLastName()).isEqualTo("Иванов");
    }

    @Test
    void getTeacherById_returns200() {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("Пётр");
        dto.setLastName("Петров");

        ResponseEntity<TeacherResponseDTO> created =
                restTemplate.postForEntity(baseUrl(), dto, TeacherResponseDTO.class);
        UUID id = created.getBody().getId();

        ResponseEntity<TeacherResponseDTO> response =
                restTemplate.getForEntity(baseUrl() + "/" + id, TeacherResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFirstName()).isEqualTo("Пётр");
    }

    @Test
    void getTeacherById_notFound_returns404() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(baseUrl() + "/" + UUID.randomUUID(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createTeacher_emptyFirstName_returns400() {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("");
        dto.setLastName("Иванов");

        ResponseEntity<String> response =
                restTemplate.postForEntity(baseUrl(), dto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void deleteTeacher_returns204_andNotFoundAfter() {
        TeacherCreateDTO dto = new TeacherCreateDTO();
        dto.setFirstName("Сидор");
        dto.setLastName("Сидоров");

        ResponseEntity<TeacherResponseDTO> created =
                restTemplate.postForEntity(baseUrl(), dto, TeacherResponseDTO.class);
        UUID id = created.getBody().getId();

        restTemplate.delete(baseUrl() + "/" + id);

        ResponseEntity<String> response =
                restTemplate.getForEntity(baseUrl() + "/" + id, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}