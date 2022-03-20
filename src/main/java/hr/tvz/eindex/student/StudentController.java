package hr.tvz.eindex.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudent(){
        return studentService.findAll();
    }

    @GetMapping("/{firstName}")
    public List<Student> getUserByFirstName(@PathVariable final String firstName){
        return studentService.findStudentByFirstName(firstName);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{firstName}")
    public void delete(@PathVariable String firstName){
        studentService.deleteByFirstName(firstName);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody final StudentCommand command){
        return studentService.save(command)
                .map(
                        studentDTO -> ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }
}
