package hr.tvz.eindex.student;

import java.util.List;
import java.util.Optional;


interface StudentRepository {

    List<Student> findAll();

    List<Student> findUserByFirstName(String firstName);

    boolean deleteByFirstName(String firstName);

    Optional<Student> save(Student student);
}
