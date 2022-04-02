package hr.tvz.eindex.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDTO> findAll();

    User findStudentById(long id);
    Optional<UserDTO> findUserByEmail(String email);
    boolean deleteById(Long firstName);

    Optional<UserDTO> save(UserCommand command);

    Optional<UserDTO> update(long id, UserCommand command);

    List<UserDTO> findProfesorByTitle();

    List<UserDTO> findStudentByTitle();
}
