package hr.tvz.eindex.kolegij;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("kolegiji")
@CrossOrigin(origins = "http://localhost:4200")
public class KolegijController {

    private final KolegijService kolegijService;

    public KolegijController(KolegijService kolegijService) {
        this.kolegijService = kolegijService;
    }

    @GetMapping
    public List<KolegijDTO> getAllKolegij(){
        return kolegijService.findAll();
    }
    @GetMapping("/{id}")
    public List<Kolegij> getKolegijById(@PathVariable final long id){
        return kolegijService.findKolegijById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        kolegijService.deleteById(id);
    }




    @PostMapping
    public ResponseEntity<KolegijDTO> save(@Valid @RequestBody final KolegijCommand command){
        return kolegijService.save(command)
                .map(
                        kolegijDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(kolegijDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }
}
