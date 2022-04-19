package hr.tvz.eindex.user;

import hr.tvz.eindex.autohority.Authority;
import hr.tvz.eindex.studentKolegij.StudentKolegij;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcUserRepository implements UserRepositoryJdbc {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


    private JdbcTemplate jdbc;
    private JdbcTemplate jdbcUserCategory;
    private JdbcTemplate jdbcUserAuthority;


    private SimpleJdbcInsert studentInserter;
    private SimpleJdbcInsert studentKolegijInserter;
    private SimpleJdbcInsert studentAuthInserter;



    public JdbcUserRepository(JdbcTemplate jdbc, JdbcTemplate jdbcUserCategory, JdbcTemplate jdbcUserAuthority){

        this.jdbc = jdbc;
        this.jdbcUserCategory = jdbcUserCategory;
        this.jdbcUserAuthority = jdbcUserAuthority;

        this.studentInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("User")
                .usingGeneratedKeyColumns("id");

        this.studentKolegijInserter = new SimpleJdbcInsert(jdbcUserCategory)
                .withTableName("StudentKolegij")
                .usingGeneratedKeyColumns("id");

        this.studentAuthInserter = new SimpleJdbcInsert(jdbcUserAuthority)
                .withTableName("user_authority")
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
    public Optional<User> findOneByUsername(String username) {
        return Optional.ofNullable(jdbc.queryForObject("Select * from User where username = ?", this::mapRowToUser, username));
    }

    @Override
    public Optional addStudentToKolegij(long studentId, long kolegijId) {
        boolean b = false;
        return Optional.ofNullable(jdbcUserCategory.update("Insert into StudentKolegij (studentid, kolegijid, ocjena, prijava) VALUES (?, ?, ?, ?)", studentId, kolegijId, 0, b));

    }

    @Override
    public Optional<Long> addOcjenaToKolegij(long studentId, long ocjena, long kolegijId) {
        int executed = 0;
         executed = jdbc.update("UPDATE StudentKolegij set " +

                        "kolegijid = ?, " +
                        "ocjena = ?"+
                "WHERE studentid = ?" +
                "AND kolegijid = ?",

                kolegijId,
                ocjena,
                studentId,
                 kolegijId
        );

        if (executed > 0) {
            return Optional.of(ocjena);
        } else {
            return Optional.empty();

        }
    }

    @Override
    public Optional<User> update(long id, User user) {
        int executed = jdbc.update("UPDATE user set " +
                        "firstName = ?, " +
                        "lastName = ?," +
                        "username = ?," +
                        "email = ?," +
                        "title = ?," +
                        "auth = ?" +
                        "WHERE id = ?",
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getTitle(),
                user.getAuthorities().getId(),
                id
        );

        if(executed > 0){
            return Optional.of(user);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional addPrijava(long kolegijid, long studentid, boolean prijava) {
       int executed = jdbc.update("UPDATE StudentKolegij set " +

                        "kolegijid = ?, " +
                        "prijava = ? "+
                        "WHERE studentid = ?" +
                        "AND kolegijid = ?",

                kolegijid,
                prijava,
                studentid,
                kolegijid
        );

        if (executed > 0) {
            return Optional.of(kolegijid);
        } else {
            return Optional.empty();

        }
    }

    @Override
    public Optional<StudentKolegij> getOcjena(long studentId, long kolegijId) {
        return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM StudentKolegij where studentid = ? AND kolegijid = ?", this::mapRowToStudentKolegij, studentId, kolegijId));
    }



    @Override
    public boolean deleteById(Long id) {
        Object[] args = new Object[] {id};
        return  jdbc.update("DELETE FROM User where id = ?",args) == 1;
    }

  //  @Override
    //public Optional<User> findUserByUsername(String username) {
  //      return Optional.ofNullable(jdbc.queryForObject("Select * from User where username = ?", this::mapRowToUser, username));
  //  }


    @Override
    public Optional<Authority> findUserAuthority(long id) {
        return Optional.ofNullable(jdbcUserAuthority.queryForObject("SELECT * FROM user_authority where user_id=?",this::mapRowToAuthority, id));
    }


    private StudentKolegij mapRowToStudentKolegij(ResultSet rs, int rowNum) throws SQLException{
        StudentKolegij studentKolegij = new StudentKolegij();
        studentKolegij.setStudentId(rs.getLong("studentid"));
        studentKolegij.setKolegijId(rs.getLong("kolegijid"));
        studentKolegij.setOcjena(rs.getLong("ocjena"));

        return studentKolegij;
    }

    private Authority mapRowToAuthority(ResultSet rs, int rowNum) throws SQLException{
        Authority authority = new Authority();
        authority.setId(rs.getLong("id"));
        authority.setName(rs.getString("name"));

        return authority;
    }




    @Override
    public Optional<User> save(final User user) {
        user.setId(saveStudentDetails(user));
        List<Authority> authorities = new ArrayList<>();
        Authority a = new Authority(2, "ROLE_USER");
        authorities.add(a);
        user.setAuthorities(a);
        jdbcUserAuthority.update("Insert into user_authority (user_id, authority_id) VALUES (?, ?)", user.getId(), "2");
        return Optional.of(user);
    }
    private long saveStudentDetails(User user){
        Map<String, Object> values = new HashMap<>();
        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("username", user.getUsername());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        values.put("password", passwordEncoder.encode(user.getPassword()));
        values.put("email", user.getEmail());
        values.put("title", user.getTitle());
        values.put("authorities", user.getAuthorities());
        values.put("auth", user.getAuthorities().getId());
        return studentInserter.executeAndReturnKey(values).longValue();
    }


    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setTitle(rs.getString("title"));
        user.setPassword(rs.getString("password"));
        return user;
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
