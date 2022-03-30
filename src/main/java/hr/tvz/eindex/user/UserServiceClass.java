package hr.tvz.eindex.user;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceClass implements UserService, Serializable {

    private final UserRepositoryJdbc userRepositoryJdbc;

    public UserServiceClass(UserRepositoryJdbc userRepositoryJdbc){
        this.userRepositoryJdbc = userRepositoryJdbc;
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

    private UserDTO mapUserToDTO(final User user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getTitle());
    }
    private User mapCommandToUser(final UserCommand command){
        return new User(command.getId(), command.getFirstName(), command.getLastName(), command.getEmail(),command.getTitle());
    }
    private UserDTO mapCommandToUserDTO(final UserCommand command){
        return new UserDTO(command.getId(), command.getFirstName(), command.getLastName(), command.getEmail(),command.getTitle());
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
    public List<UserDTO> findProfesorByTitle() {
        return userRepositoryJdbc.findProfesorByTitle().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findStudentByTitle() {
        return userRepositoryJdbc.findStudentByTitle().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

}
