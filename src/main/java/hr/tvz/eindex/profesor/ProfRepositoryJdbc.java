package hr.tvz.eindex.profesor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface ProfRepositoryJdbc {

    List<Profesor> findAll();

    List<Profesor> findProfesorByFirstName(String firstName);

    boolean deleteByFirstName(String firstName);

    Optional<Profesor> save(Profesor profesor);
}
