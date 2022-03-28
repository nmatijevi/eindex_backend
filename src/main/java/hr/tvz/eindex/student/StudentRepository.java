package hr.tvz.eindex.student;

import java.util.List;
import java.util.Optional;


interface StudentRepository {

    List<Student> findAll();

    Student findUserById(long id);

    boolean deleteById(Long id);

    Optional<Student> save(Student student);

    Optional<Student> update(long id, Student student);
}
