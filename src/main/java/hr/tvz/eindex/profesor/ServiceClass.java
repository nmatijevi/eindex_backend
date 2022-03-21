package hr.tvz.eindex.profesor;

import hr.tvz.eindex.student.Student;
import hr.tvz.eindex.student.StudentCommand;
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
        return profRepositoryJdbc.deleteByFirstName(firstName);
    }

    @Override
    public Optional<ProfesorDTO> save(ProfesorCommand command) {
        return profRepositoryJdbc.save(mapCommandToProfesor(command)).map(this::mapProfesorToDTO);
    }
    private Profesor mapCommandToProfesor(final ProfesorCommand command){
        return new Profesor(command.getId(), command.getFirstName(), command.getLastName(), command.getEmail(),command.getTitle());
    }

    private ProfesorDTO mapProfesorToDTO(final Profesor profesor){
        return new ProfesorDTO(profesor.getId(), profesor.getFirstName(), profesor.getLastName(), profesor.getEmail(),profesor.getTitle());
    }
}


