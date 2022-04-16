package hr.tvz.eindex.user;

import hr.tvz.eindex.autohority.Authority;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceClass implements UserService, Serializable {

    private final UserRepositoryJdbc userRepositoryJdbc;
    private final UserRepoJpa userRepoJpa;
    public UserServiceClass(UserRepositoryJdbc userRepositoryJdbc, UserRepoJpa userRepoJpa){
        this.userRepositoryJdbc = userRepositoryJdbc;
        this.userRepoJpa = userRepoJpa;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepositoryJdbc.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

 //   @Override
  //  public UserDTO findUserByFirstName(String firstName) {
 //       return userRepositoryJdbc.findUserByFirstName(firstName).map(this::mapUserToDTO).orElse(null);
  //  }
    @Override
    public User findStudentById(long id) {
        return userRepositoryJdbc.findStudentById(id);
    }

    @Override
    public User findOneById(long id) {
        return userRepoJpa.findOneById(id);
    }

    // @Override
   // public Optional<UserDTO> findUserByUsername(String email) {
   //     return userRepositoryJdbc.findUserByUsername(email).map(this::mapUserToDTO);
   // }

    private UserDTO mapUserToDTO(final User user){
        return new UserDTO(user.getId(), user.getFirstName() ,user.getLastName(), user.getUsername(), user.getEmail(), user.getTitle(), user.getPassword(),
                user.getAuthorities().getName());
    }


    private User mapCommandToUser(final UserCommand command){
        return new User(command.getId(), command.getFirstName(), command.getLastName(), command.getUsername(),
                command.getEmail(),command.getTitle(), command.getPassword(), command.getAuthority());
    }
    /*
    private UserDTO mapCommandToUserDTO(final UserCommand command){
        return new UserDTO(command.getId(), command.getFirstName(), command.getLastName(), command.getEmail(), command.getEmail(),command.getTitle(), command.getPassword());
    }*/

    @Override
    public Optional<UserDTO> findOneByUsername(String username) {
        return userRepoJpa.findOneByUsername(username).map(this::mapUserToDTO);
    }

    @Override
    public boolean deleteById(Long id) {
        return userRepositoryJdbc.deleteById(id);
    }

    @Override
    public Optional<UserDTO> save(UserCommand command) {
        return userRepositoryJdbc.save(mapCommandToUser(command)).map(this::mapUserToDTO);
    }

    @Override
    public Optional<UserDTO> update(long id, UserCommand command) {
        return userRepositoryJdbc.update(id, mapCommandToUser(command)).map(this::mapUserToDTO);
    }

    @Override
    public List<UserDTO> findAllByTitle(String title) {
        return userRepoJpa.findAllByTitle(title).stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getStudentsByKolegijId(long id) {
        return userRepoJpa.findAllByKolegijList_id(id).stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional addStudentToKolegij(long studentId, long kolegijId) {
        return userRepositoryJdbc.addStudentToKolegij(studentId, kolegijId);
    }

    @Override
    public Optional<Authority> findUserAuthority(long id) {
        return userRepositoryJdbc.findUserAuthority(id);
    }

/* @Override
    public List<UserDTO> findAllByTitle() {
        return userRepoJpa.findAllByTitle("Student").stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    */

}
