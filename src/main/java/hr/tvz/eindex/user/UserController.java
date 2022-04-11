package hr.tvz.eindex.user;

import hr.tvz.eindex.kolegij.KolegijDTO;
import hr.tvz.eindex.security.DomainUserDetailsService;
import hr.tvz.eindex.security.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/user", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200/%22")
public class UserController {

    private final DomainUserDetailsService domainUserDetailsService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(DomainUserDetailsService domainUserDetailsService, UserService userService) {
        this.domainUserDetailsService = domainUserDetailsService;
        this.modelMapper = new ModelMapper();
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
        return userService.findAllByTitle("Student");
    }

    @GetMapping("/kolegij/{id}")
    public List<UserDTO> getStudentsByKolegijId(@PathVariable Long id){
        return userService.getStudentsByKolegijId(id);
    }

    @GetMapping("/allProfessors")
    public List<UserDTO> getProfesorByTitle(){
        return userService.findAllByTitle("Profesor");
    }

    @PostMapping("/addStudent/{studentId}/{kolegijId}")
    public void add(@PathVariable String studentId, @PathVariable String kolegijId){
        userService.addStudentToKolegij(Integer.parseInt(kolegijId), Integer.parseInt(studentId));

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
        return SecurityUtils.getCurrentUserUsername().map(
                username -> userService.findOneByUsername(username).map
                        (ResponseEntity::ok).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                )
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
        );
    }
}
