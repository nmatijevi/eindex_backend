package hr.tvz.eindex.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUser(){
        return userService.findAll();
    }

    @GetMapping("/{firstName}")
    public List<User> getUserByFirstName(@PathVariable final String firstName){
        return userService.findUserByFirstName(firstName);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{firstName}")
    public void delete(@PathVariable String firstName){
        userService.deleteByFirstName(firstName);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody final UserCommand command){
        return userService.save(command)
                .map(
                        userDTO -> ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(userDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }
}
