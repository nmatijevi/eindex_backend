package hr.tvz.eindex.user;

import hr.tvz.eindex.autohority.Authority;
import hr.tvz.eindex.kolegij.KolegijDTO;
import hr.tvz.eindex.security.DomainUserDetailsService;
import hr.tvz.eindex.security.SecurityUtils;
import hr.tvz.eindex.studentKolegij.StudentKolegij;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public List<UserDTO> getAllStudent(){
        logger.info("Metoda getAllStudents ");
        return userService.findAll();
    }

    @GetMapping("/edit/{id}")
    public User getUserById(@PathVariable final long id){

        logger.info("Metoda getUserById radi ");

        return userService.findStudentById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        logger.info("Metoda delete radi ");

        userService.deleteById(id);
    }

    @GetMapping("/allStudents")
    public List<UserDTO> getStudentByTitle(){
        logger.info("metoda getStudentByTitle radi");
        return userService.findAllByTitle("Student");
    }

    @GetMapping("/kolegij/{id}")
    public List<UserDTO> getStudentsByKolegijId(@PathVariable Long id){
        logger.info("metoda getStudentsByKolegijId radi");

        return userService.getStudentsByKolegijId(id);
    }

    @PostMapping("/grade/{studentId}/{ocjena}/{kolegijId}")
    public void add(@PathVariable String studentId, @PathVariable String ocjena, @PathVariable String kolegijId){
        logger.info("metoda add radi");

        userService.addOcjena(Integer.parseInt(studentId), Integer.parseInt(ocjena), Integer.parseInt(kolegijId));
    }

    @GetMapping("/grades/{studentid}/{kolegijid}")
    public Optional<StudentKolegij> getOcjena(@PathVariable String studentid, @PathVariable String kolegijid){
        logger.info("metoda getOcjena radi");

        return userService.getOcjena(Integer.parseInt(studentid),Integer.parseInt(kolegijid));
    }

    @GetMapping("/getGrades/{studentid}/{kolegijid}")
    public List<StudentKolegij> getOcjenaArray(@PathVariable String studentid, @PathVariable String kolegijid){
        logger.info("metoda getOcjena radi");
        return userService.getOcjenaArray(Integer.parseInt(studentid),Integer.parseInt(kolegijid));
    }

    @PostMapping("/setExam/{kolegijId}/{studentId}/{prijava}")
    public void addExam(@PathVariable String kolegijId,  @PathVariable String studentId, @PathVariable String prijava){
        logger.info("metoda addExam radi");

        userService.addPrijavaIspita( Integer.parseInt(kolegijId), Integer.parseInt(studentId), Boolean.parseBoolean(prijava));
    }

    @GetMapping("/allProfessors")
    public List<UserDTO> getProfesorByTitle(){
        logger.info("metoda getProfesorByTitle radi");

        return userService.findAllByTitle("Profesor");
    }

    @PostMapping("/addStudent/{studentId}/{kolegijId}")
    public void add(@PathVariable String studentId, @PathVariable String kolegijId){
        logger.info("metoda addStudent radi");
        userService.addStudentToKolegij(Integer.parseInt(kolegijId), Integer.parseInt(studentId));

    }




    @PostMapping("/add")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody final UserCommand command){
        logger.info("metoda saveStudent radi");

        List<Authority> authorities = new ArrayList<>();
        Authority a = new Authority(2, "ROLE_USER");
        authorities.add(a);
        command.setAuthority(a);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable long id, @Valid @RequestBody final UserCommand command){
        logger.info("metoda update radi");

        List<Authority> authorities = new ArrayList<>();
        Authority a = new Authority(2, "ROLE_USER");
        authorities.add(a);
        command.setAuthority(a);
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
