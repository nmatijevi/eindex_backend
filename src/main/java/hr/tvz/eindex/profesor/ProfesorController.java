package hr.tvz.eindex.profesor;

import hr.tvz.eindex.student.Student;
import hr.tvz.eindex.student.StudentCommand;
import hr.tvz.eindex.student.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("profesor")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<ProfesorDTO> getALlProfesor(){
        return profesorService.findAll();
    }

    @GetMapping("/{firstName}")
    public List<Profesor> getProfesorByFirstName(@PathVariable final String firstName){
        return profesorService.findProfesorByFirstName(firstName);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{firstName}")
    public void delete(@PathVariable String firstName){
        profesorService.deleteByFirstName(firstName);
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> save(@Valid @RequestBody final ProfesorCommand command){
        return profesorService.save(command)
                .map(
                        profesorDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(profesorDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }
}
