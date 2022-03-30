package hr.tvz.eindex.user;

import java.util.List;
import java.util.Optional;


interface UserRepository {

    List<User> findAll();

    User findUserById(long id);

    boolean deleteById(Long id);

    Optional<User> save(User user);

    Optional<User> update(long id, User user);
}
