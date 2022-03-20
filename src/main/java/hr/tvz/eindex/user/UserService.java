package hr.tvz.eindex.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDTO> findAll();

    List<User> findUserByFirstName(String firstName);

    boolean deleteByFirstName(String firstName);

    Optional<UserDTO> save(UserCommand command);

}
