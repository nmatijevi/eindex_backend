package hr.tvz.eindex.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    List<User> findAll();

    User findUserById(long id);

    boolean deleteById(Long id);
    Optional<UserDTO> findOneByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> save(User user);

    Optional<User> update(long id, User user);
}
