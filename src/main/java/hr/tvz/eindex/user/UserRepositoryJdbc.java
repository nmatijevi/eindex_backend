package hr.tvz.eindex.user;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface UserRepositoryJdbc {
    List<User> findAll();

    User findStudentById(long id);
    Optional<User> findUserByEmail(String email);
    boolean deleteById(Long id);

    Optional<User> save(User user);
    Optional<User> update(long id, User user);

    List<User>findStudentByTitle();

    List<User>findProfesorByTitle();

}
