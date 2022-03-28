package hr.tvz.eindex.student;

import hr.tvz.eindex.kolegij.KolegijCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    List<StudentDTO> findAll();

    Student findStudentById(long id);

    boolean deleteById(Long firstName);

    Optional<StudentDTO> save(StudentCommand command);

    Optional<StudentDTO> update(long id, StudentCommand command);
}
