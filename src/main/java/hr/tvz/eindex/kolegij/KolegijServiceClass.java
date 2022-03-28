package hr.tvz.eindex.kolegij;

import hr.tvz.eindex.profesor.Profesor;
import hr.tvz.eindex.profesor.ProfesorCommand;
import hr.tvz.eindex.profesor.ProfesorDTO;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KolegijServiceClass implements KolegijService, Serializable {

    private final JdbcKolegijRepository jdbcKolegijRepository;

    public KolegijServiceClass(JdbcKolegijRepository jdbcKolegijRepository) {
        this.jdbcKolegijRepository = jdbcKolegijRepository;
    }

    @Override
    public List<KolegijDTO> findAll() {
        return jdbcKolegijRepository.findAll().stream().map(this::mapKolegijToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Kolegij> findKolegijById(Long id) {
        return jdbcKolegijRepository.findKolegijById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcKolegijRepository.deleteById(id);
    }

    @Override
    public Optional<KolegijDTO> save(KolegijCommand command) {
        return jdbcKolegijRepository.save(mapCommandToKolegij(command)).map(this::mapKolegijToDTO);
    }
    private KolegijDTO mapKolegijToDTO(final Kolegij kolegij){
        return new KolegijDTO(kolegij.getId(), kolegij.getName());
    }
    private Kolegij mapCommandToKolegij(final KolegijCommand command){
        return new Kolegij(command.getId(), command.getName());
    }
}
