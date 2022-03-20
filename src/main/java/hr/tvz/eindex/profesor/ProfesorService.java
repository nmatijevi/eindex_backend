package hr.tvz.eindex.profesor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProfesorService {

    List<ProfesorDTO> findAll();

    List<Profesor> findProfesorByFirstName(String firstName);

    boolean deleteByFirstName(String firstName);

    Optional<ProfesorDTO> save(ProfesorCommand command);
}
