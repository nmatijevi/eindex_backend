package hr.tvz.eindex.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepoJpa extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findOneByUsername(String username);
    User findOneById(long id);

    List<User> findAllByTitle(String title);
    List<User> findAllByKolegijList_id(long id);
    Optional<User> findOcjenaByKolegijList_id(long studentid);
}
