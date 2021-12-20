package br.com.alura.school.enrollment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EnrollmentControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_add_new_enrollment() throws Exception {
        NewEnrollmentRequest newEnrollment = new NewEnrollmentRequest("alex");

        mockMvc.perform(post("/courses/java-3/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnrollment)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_not_allow_duplication_of_user_enroll_course() throws Exception {
        NewEnrollmentRequest newEnrollment = new NewEnrollmentRequest("alex");

        mockMvc.perform(post("/courses/java-1/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(newEnrollment)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_retrieve_enroll_report() throws Exception {
        mockMvc.perform(get("/courses/enroll/report")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].email", is("ana@email.com")))
                .andExpect(jsonPath("$[0].quantidade-de-matriculas", is(3)))
                .andExpect(jsonPath("$[1].email", is("alex@email.com")))
                .andExpect(jsonPath("$[1].quantidade-de-matriculas", is(1)));
    }

    @Test
    @Sql(statements = "DELETE FROM Enrollment")
    void no_content_when_user_enroll_course_not_exist() throws Exception {
        mockMvc.perform(get("/courses/enroll/report")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
