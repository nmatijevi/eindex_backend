package hr.tvz.eindex.kolegij;

import hr.tvz.eindex.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KolegijRepoJpa extends JpaRepository<Kolegij, Long> {

    List<Kolegij> findAllByUserList_id(long id);

}
