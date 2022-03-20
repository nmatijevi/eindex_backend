package hr.tvz.eindex.profesor;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceClass implements ProfesorService, Serializable {

    private final ProfRepositoryJdbc profRepositoryJdbc;

    public ServiceClass(ProfRepositoryJdbc profRepositoryJdbc) {
        this.profRepositoryJdbc = profRepositoryJdbc;
    }

    @Override
    public List<ProfesorDTO> findAll() {
        return profRepositoryJdbc.findAll().stream().map(this::mapProfesorToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Profesor> findProfesorByFirstName(String firstName) {
        return profRepositoryJdbc.findProfesorByFirstName(firstName);
    }

    @Override
    public boolean deleteByFirstName(String firstName) {
        return false;
    }

    @Override
    public Optional<ProfesorDTO> save(ProfesorCommand command) {
        return Optional.empty();
    }


    private ProfesorDTO mapProfesorToDTO(final Profesor profesor){
        return new ProfesorDTO(profesor.getId(), profesor.getFirstName(), profesor.getLastName(), profesor.getEmail(),profesor.getTitle());
    }
}


