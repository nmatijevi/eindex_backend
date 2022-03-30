package hr.tvz.eindex.kolegij;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcKolegijRepository implements KolegijRepositoryJdbc {


    private JdbcTemplate jdbc;
    private SimpleJdbcInsert kolegijInserter;

    public JdbcKolegijRepository(JdbcTemplate jdbc){

        this.jdbc = jdbc;
        this.kolegijInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Kolegij")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Kolegij> findAll() {
        return jdbc.query("select * from Kolegij", this::mapRowToKolegij);
    }

    @Override
    public List<Kolegij> findKolegijById(final Long id) {
        return jdbc.query("Select * from Kolegij where id = ?", this::mapRowToKolegij, id);
    }

    @Override
    public boolean deleteById(Long id) {
        Object[] args = new Object[] {id};
        return  jdbc.update("DELETE FROM Kolegij where id = ?",args) == 1;
    }

    @Override
    public Optional<Kolegij> save(final Kolegij kolegij) {
        kolegij.setId(saveKolegijDetails(kolegij));
        return Optional.of(kolegij);
    }
    private long saveKolegijDetails(Kolegij kolegij){
        Map<String, Object> values = new HashMap<>();
        values.put("name", kolegij.getName());
        return kolegijInserter.executeAndReturnKey(values).longValue();
    }


    private Kolegij mapRowToKolegij(ResultSet rs, int rowNum) throws SQLException {
        Kolegij kolegij = new Kolegij();
        kolegij.setId(rs.getLong("id"));
        kolegij.setName(rs.getString("name"));

        return kolegij;
    }

}
