package hr.tvz.eindex.student;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface StudentRepositoryJdbc {
    List<Student> findAll();

    List<Student> findStudentByFirstName(String firstName);

    boolean deleteById(Long id);

    Optional<Student> save(Student student);
}
