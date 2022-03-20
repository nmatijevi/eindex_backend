package hr.tvz.eindex.user;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface userRepositoryJdbc {
    List<User> findAll();

    List<User> findUserByFirstName(String firstName);

    boolean deleteByFirstName(String firstName);

    Optional<User> save(User user);
}
