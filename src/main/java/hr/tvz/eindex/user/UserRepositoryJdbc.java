package hr.tvz.eindex.user;

import hr.tvz.eindex.autohority.Authority;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface UserRepositoryJdbc {
    List<User> findAll();

    User findStudentById(long id);
    //Optional<User> findUserByUsername(String username);
    boolean deleteById(Long id);
    Optional<User> findOneByUsername(String username);
    Optional<User> addStudentToKolegij(long studentId, long kolegijId);

    Optional<Authority> findUserAuthority(long id);
    Optional<User> save(User user);
    Optional<User> update(long id, User user);
    Optional<User> addOcjenaToKolegij(long studentId, long kolegijId, long ocjenaId);

    //List<User> getStudentsByKolegijId(long id);
    List<User>findStudentByTitle();

    List<User>findProfesorByTitle();

}
