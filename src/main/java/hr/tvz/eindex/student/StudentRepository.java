package hr.tvz.eindex.student;

import java.util.List;
import java.util.Optional;


interface StudentRepository {

    List<Student> findAll();

    List<Student> findUserByFirstName(String firstName);

    boolean deleteById(Long id);

    Optional<Student> save(Student student);
}
