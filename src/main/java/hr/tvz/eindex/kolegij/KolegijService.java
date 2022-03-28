package hr.tvz.eindex.kolegij;

import hr.tvz.eindex.profesor.Profesor;
import hr.tvz.eindex.profesor.ProfesorCommand;
import hr.tvz.eindex.profesor.ProfesorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface KolegijService {


    List<KolegijDTO> findAll();

    List<Kolegij> findKolegijById(Long id);

    boolean deleteById(Long id);

    Optional<KolegijDTO> save(KolegijCommand command);
}
