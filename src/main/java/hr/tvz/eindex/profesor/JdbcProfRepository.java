package hr.tvz.eindex.profesor;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class JdbcProfRepository implements ProfRepositoryJdbc{

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert profInserter;

    public JdbcProfRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.profInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Profesor")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Profesor> findAll() {
       return jdbc.query("select * from Profesor", this::mapRowToProfesor);
    }

    @Override
    public List<Profesor> findProfesorByFirstName(String firstName) {
        return jdbc.query("Select * from Profesor where firstName = ?", this::mapRowToProfesor, firstName);
    }

    @Override
    public boolean deleteByFirstName(String firstName) {
        return false;
    }



    @Override
    public Optional<Profesor> save(Profesor profesor) {
        return Optional.empty();
    }


    private Profesor mapRowToProfesor(ResultSet rs, int rowNum) throws SQLException {
        Profesor profesor = new Profesor();
        profesor.setId(rs.getLong("id"));
        profesor.setFirstName(rs.getString("firstName"));
        profesor.setLastName(rs.getString("lastName"));
        profesor.setEmail(rs.getString("email"));
        profesor.setTitle(rs.getString("title"));
        return profesor;
    }
}
