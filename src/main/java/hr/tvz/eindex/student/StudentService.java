package hr.tvz.eindex.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    List<StudentDTO> findAll();

    List<Student> findStudentByFirstName(String firstName);

    boolean deleteByFirstName(String firstName);

    Optional<StudentDTO> save(StudentCommand command);

}
