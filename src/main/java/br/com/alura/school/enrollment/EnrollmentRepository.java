package br.com.alura.school.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    @Query("select new br.com.alura.school.enrollment.EnrollmentResponse(e.user.email, count(e)) from Enrollment e group by e.user.id order by count(e) desc")
    List<EnrollmentResponse> enrollReport();
}
