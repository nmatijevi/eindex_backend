package hr.tvz.eindex.student;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceClass implements StudentService, Serializable {

    private final StudentRepositoryJdbc studentRepositoryJdbc;

    public StudentServiceClass(StudentRepositoryJdbc studentRepositoryJdbc){
        this.studentRepositoryJdbc = studentRepositoryJdbc;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepositoryJdbc.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

 //   @Override
  //  public UserDTO findUserByFirstName(String firstName) {
 //       return userRepositoryJdbc.findUserByFirstName(firstName).map(this::mapUserToDTO).orElse(null);
  //  }
    @Override
    public List<Student> findStudentByFirstName(String firstName) {
        return studentRepositoryJdbc.findStudentByFirstName(firstName);
    }

    private StudentDTO mapUserToDTO(final Student student){
        return new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getTitle());
    }
    private Student mapCommandToUser(final StudentCommand command){
        return new Student(command.getId(), command.getFirstName(), command.getLastName(), command.getTitle());
    }
    private StudentDTO mapCommandToUserDTO(final StudentCommand command){
        return new StudentDTO(command.getId(), command.getFirstName(), command.getLastName(), command.getTitle());
    }

    @Override
    public boolean deleteByFirstName(String firstName) {
        return studentRepositoryJdbc.deleteByFirstName(firstName);
    }

    @Override
    public Optional<StudentDTO> save(StudentCommand command) {
        return studentRepositoryJdbc.save(mapCommandToUser(command)).map(this::mapUserToDTO);
    }
}
