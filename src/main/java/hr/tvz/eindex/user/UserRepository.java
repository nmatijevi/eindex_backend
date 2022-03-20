package hr.tvz.eindex.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


interface UserRepository {

    List<User> findAll();

    List<User> findUserByFirstName(String firstName);

    boolean deleteByFirstName(String firstName);

    Optional<User> save(User user);
}
