package hr.tvz.eindex.profesor;

import hr.tvz.eindex.student.Student;
import org.springframework.web.bind.annotation.*;

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
}
