package hr.tvz.eindex.user;

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
public class jdbcUserRepository implements userRepositoryJdbc {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert userInserter;

    public jdbcUserRepository(JdbcTemplate jdbc){

        this.jdbc = jdbc;
        this.userInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<User> findAll() {
        return jdbc.query("select * from Users", this::mapRowToUsers);
    }

    @Override
    public List<User> findUserByFirstName(final String firstName) {
            return jdbc.query("Select * from Users where firstName = ?", this::mapRowToUsers, firstName);
        }

    @Override
    public boolean deleteByFirstName(String firstName) {
        return false;
    }

    @Override
    public Optional<User> save(User user) {
        return Optional.empty();
    }

    private User mapRowToUsers(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setTitle(rs.getString("title"));
        return user;
    }
}
