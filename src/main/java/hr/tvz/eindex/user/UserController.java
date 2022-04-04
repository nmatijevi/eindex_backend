package hr.tvz.eindex.user;

import hr.tvz.eindex.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllStudent(){
        return userService.findAll();
    }

    @GetMapping("/id")
    public User getUserById(@PathVariable final long id){
        return userService.findStudentById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping("/allStudents")
    public List<UserDTO> getStudentByTitle(){
        return userService.findStudentByTitle();
    }

    @GetMapping("/allProfessors")
    public List<UserDTO> getProfesorByTitle(){
        return userService.findProfesorByTitle();
    }

    @PostMapping("/add")
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

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable long id, @Valid @RequestBody
    final UserCommand command){
        return userService.update(id, command)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(){
        return SecurityUtils.getCurrentUserEmail().map(
                email -> userService.findUserByEmail(email).map
                        (ResponseEntity::ok).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                )
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
        );
    }
}
