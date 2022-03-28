package hr.tvz.eindex.profesor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface ProfRepositoryJdbc {

    List<Profesor> findAll();

    List<Profesor> findProfesorById(long id);

    boolean deleteById(Long id);

    Optional<Profesor> save(Profesor profesor);
}
