package hr.tvz.eindex.kolegij;


import hr.tvz.eindex.user.UserDTO;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KolegijServiceClass implements KolegijService, Serializable {

    private final JdbcKolegijRepository jdbcKolegijRepository;
    private final KolegijRepoJpa kolegijRepoJpa;

    public KolegijServiceClass(JdbcKolegijRepository jdbcKolegijRepository, KolegijRepoJpa kolegijRepoJpa) {
        this.jdbcKolegijRepository = jdbcKolegijRepository;
        this.kolegijRepoJpa = kolegijRepoJpa;
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

    @Override
    public List<KolegijDTO> getKolegijByStudentsId(long id) {
        return jdbcKolegijRepository.findKolegijById(id).stream().map(this::mapKolegijToDTO).collect(Collectors.toList());
    }

    private KolegijDTO mapKolegijToDTO(final Kolegij kolegij){
        return new KolegijDTO(kolegij.getId(), kolegij.getName());
    }
    private Kolegij mapCommandToKolegij(final KolegijCommand command){
        return new Kolegij(command.getId(), command.getName());
    }

    @Override
    public List<KolegijDTO> getKolegijByStudentId(long id) {
            return kolegijRepoJpa.findAllByUserList_id(id).stream().map(this::mapKolegijToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional addOcjena(int studentId, int ocjena, int kolegijId) {
        return jdbcKolegijRepository.addOcjenaToKolegij(studentId, ocjena, kolegijId);
    }
}
