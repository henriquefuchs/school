package br.com.alura.school.enrollment;

import br.com.alura.school.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class NewEnrollmentRequest {

    @Size(max = 20)
    @NotBlank
    @JsonProperty
    private String username;

    public NewEnrollmentRequest() {
    }

    NewEnrollmentRequest(String username) {
        this.username = username;
    }

    User toEntity() {
        return new User(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
