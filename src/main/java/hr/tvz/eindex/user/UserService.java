package hr.tvz.eindex.user;

import hr.tvz.eindex.autohority.Authority;
import hr.tvz.eindex.studentKolegij.StudentKolegij;
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
    User findOneById(long id);

    Optional<UserDTO> save(UserCommand command);

    Optional<UserDTO> update(long id, UserCommand command);

    Optional<Authority> findUserAuthority(long id);

    List<UserDTO> findAllByTitle(String title);

    List<UserDTO> getStudentsByKolegijId(long id);

    Optional addStudentToKolegij(long studentId, long kolegijId);

    Optional addOcjena(int studentId, int ocjena, int kolegijId);

    Optional<StudentKolegij> getOcjena(int studentId, int kolegijId);
    List<StudentKolegij> getOcjenaArray(int studentId, int kolegijId);

    Optional addPrijavaIspita(int kolegijId, int studentId, boolean prijava);

    // List<UserDTO> findStudentByTitle();
}
