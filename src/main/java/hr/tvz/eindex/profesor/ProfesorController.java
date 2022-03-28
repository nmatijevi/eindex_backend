package hr.tvz.eindex.profesor;

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

    @GetMapping("/{id}")
    public List<Profesor> getProfesorByFirstName(@PathVariable final long id){
        return profesorService.findProfesorById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        profesorService.deleteById(id);
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
