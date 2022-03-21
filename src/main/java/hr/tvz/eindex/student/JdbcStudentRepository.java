package hr.tvz.eindex.student;

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
public class JdbcStudentRepository implements StudentRepositoryJdbc {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert studentInserter;

    public JdbcStudentRepository(JdbcTemplate jdbc){

        this.jdbc = jdbc;
        this.studentInserter = new SimpleJdbcInsert(jdbc)
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
        Object[] args = new Object[] {firstName};
        return  jdbc.update("DELETE FROM Student where firstName = ?",args) == 1;
    }

    @Override
    public Optional<Student> save(final Student student) {
        student.setId(saveStudentDetails(student));
        return Optional.of(student);
    }
    private long saveStudentDetails(Student student){
        Map<String, Object> values = new HashMap<>();
        values.put("firstName", student.getFirstName());
        values.put("lastName", student.getLastName());
        values.put("email", student.getEmail());
        values.put("title", student.getTitle());
        return studentInserter.executeAndReturnKey(values).longValue();
    }


    private Student mapRowToStudents(ResultSet rs, int rowNum) throws SQLException{
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        student.setEmail(rs.getString("email"));
        student.setTitle(rs.getString("title"));
        return student;
    }
}
