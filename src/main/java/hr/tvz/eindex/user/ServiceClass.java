package hr.tvz.eindex.user;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceClass implements UserService, Serializable {

    private final userRepositoryJdbc userRepositoryJdbc;

    public ServiceClass(userRepositoryJdbc userRepositoryJdbc){
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
    public List<User> findUserByFirstName(String firstName) {
        return userRepositoryJdbc.findUserByFirstName(firstName);
    }

    private UserDTO mapUserToDTO(final User user){
        return new UserDTO(user.getId(),user.getFirstName(), user.getLastName(), user.getTitle());
    }
    private User mapCommandToUser(final UserCommand command){
        return new User(command.getId(), command.getFirstName(), command.getLastName(), command.getTitle());
    }
    private UserDTO mapCommandToUserDTO(final UserCommand command){
        return new UserDTO(command.getId(), command.getFirstName(), command.getLastName(), command.getTitle());
    }

    @Override
    public boolean deleteByFirstName(String firstName) {
        return userRepositoryJdbc.deleteByFirstName(firstName);
    }

    @Override
    public Optional<UserDTO> save(UserCommand command) {
        return userRepositoryJdbc.save(mapCommandToUser(command)).map(this::mapUserToDTO);
    }
}
