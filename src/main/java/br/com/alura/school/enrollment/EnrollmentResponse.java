package br.com.alura.school.enrollment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnrollmentResponse {

    @JsonProperty
    private final String email;

    @JsonProperty(value = "quantidade-de-matriculas")
    private Long enrollment;

    public EnrollmentResponse(String email, Long enrollment) {
        this.email = email;
        this.enrollment = enrollment;
    }
}
