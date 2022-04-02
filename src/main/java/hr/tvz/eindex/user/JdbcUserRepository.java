package hr.tvz.eindex.user;

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
public class JdbcUserRepository implements UserRepositoryJdbc {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert studentInserter;

    public JdbcUserRepository(JdbcTemplate jdbc){

        this.jdbc = jdbc;
        this.studentInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("User")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<User> findAll() {
        return jdbc.query("select * from User", this::mapRowToUser);
    }

    @Override
    public User findStudentById(final long id) {
            return jdbc.queryForObject("Select * from User where id = ?", this::mapRowToUser, id);
        }


    @Override
    public boolean deleteById(Long id) {
        Object[] args = new Object[] {id};
        return  jdbc.update("DELETE FROM User where id = ?",args) == 1;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(jdbc.queryForObject("Select * from User where email = ?", this::mapRowToUser, email));
    }

    @Override
    public Optional<User> save(final User user) {
        user.setId(saveStudentDetails(user));
        return Optional.of(user);
    }
    private long saveStudentDetails(User user){
        Map<String, Object> values = new HashMap<>();
        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("email", user.getEmail());
        values.put("title", user.getTitle());
        return studentInserter.executeAndReturnKey(values).longValue();
    }


    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setTitle(rs.getString("title"));
        return user;
    }

    @Override
    public Optional<User> update(long id, User user) {
        int executed = jdbc.update("UPDATE user set " +
                "firstName = ?, " +
                "lastName = ?," +
                "email = ?," +
                "title = ?" +
                "WHERE id = ?",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTitle(),
                user.getId()
                );

       if(executed > 0){
           return Optional.of(user);
       }else{
           return Optional.empty();
       }
    }

    @Override
    public List<User> findStudentByTitle() {
        return jdbc.query("SELECT * from USER where title ='Student'", this::mapRowToUser);
    }

    @Override
    public List<User> findProfesorByTitle() {
        return jdbc.query("SELECT * from USER where title ='Profesor'", this::mapRowToUser);
    }

}
