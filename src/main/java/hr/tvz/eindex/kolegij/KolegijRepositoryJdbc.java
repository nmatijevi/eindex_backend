package hr.tvz.eindex.kolegij;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface KolegijRepositoryJdbc {

     List<Kolegij> findAll();
     List<Kolegij> findKolegijById(final Long id);
     boolean deleteById(Long id);
     Optional<Kolegij> save(final Kolegij kolegij);
}
