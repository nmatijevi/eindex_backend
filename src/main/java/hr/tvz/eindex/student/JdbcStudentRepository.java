package hr.tvz.eindex.student;

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
public class JdbcStudentRepository implements StudentRepositoryJdbc {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert userInserter;

    public JdbcStudentRepository(JdbcTemplate jdbc){

        this.jdbc = jdbc;
        this.userInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Student")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Student> findAll() {
        return jdbc.query("select * from Student", this::mapRowToStudents);
    }

    @Override
    public List<Student> findStudentByFirstName(final String firstName) {
            return jdbc.query("Select * from Student where firstName = ?", this::mapRowToStudents, firstName);
        }

    @Override
    public boolean deleteByFirstName(String firstName) {
        return false;
    }

    @Override
    public Optional<Student> save(Student student) {
        return Optional.empty();
    }

    private Student mapRowToStudents(ResultSet rs, int rowNum) throws SQLException{
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        student.setTitle(rs.getString("title"));
        return student;
    }
}
