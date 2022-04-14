package hr.tvz.eindex.kolegij;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface KolegijService {


    List<KolegijDTO> findAll();


    List<Kolegij> findKolegijById(Long id);

    boolean deleteById(Long id);

    Optional<KolegijDTO> save(KolegijCommand command);

    List<KolegijDTO> getKolegijByStudentsId(long id);

    List<KolegijDTO> getKolegijByStudentId(long id);
}
