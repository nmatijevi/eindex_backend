package hr.tvz.eindex.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDTO> findAll();

    User findStudentById(long id);
    //Optional<UserDTO> findUserByUsername(String username);
    boolean deleteById(Long firstName);
    Optional<UserDTO> findOneByUsername(String username);

    Optional<UserDTO> save(UserCommand command);

    Optional<UserDTO> update(long id, UserCommand command);

    List<UserDTO> findAllByTitle(String title);

    List<UserDTO> getStudentsByKolegijId(long id);

   // List<UserDTO> findStudentByTitle();
}
