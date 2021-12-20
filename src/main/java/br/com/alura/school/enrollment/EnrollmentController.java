package br.com.alura.school.enrollment;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class EnrollmentController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public EnrollmentController(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/courses/{courseCode}/enroll")
    ResponseEntity<Void> newEnrollment(@PathVariable String courseCode, @RequestBody @Valid NewEnrollmentRequest newEnrollmentRequest) {
        Course course = courseRepository.findByCode(courseCode).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", courseCode)));

        String username = newEnrollmentRequest.toEntity().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User with userame %s not found", username)));

        try {
            user.addCourse(course);
            userRepository.flush();
        } catch (Exception e) {
            throw new ResponseStatusException(BAD_REQUEST);
        }

        URI location = URI.create("");
        return ResponseEntity.created(location).build();
    }
}
