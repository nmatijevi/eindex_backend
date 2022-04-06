package hr.tvz.eindex.user;


import hr.tvz.eindex.autohority.Authority;
import hr.tvz.eindex.kolegij.Kolegij;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String title;

    private String password;

    @ManyToMany(targetEntity = Authority.class, mappedBy = "users")
    private Set<Authority> authorities;

     @ManyToMany(targetEntity = Kolegij.class)
     @JoinTable(
             name = "StudentKolegij",
             joinColumns = { @JoinColumn(name = "studentId") },
             inverseJoinColumns = { @JoinColumn(name = "kolegijId") }
     )
     private List<Kolegij> kolegijList;




    public User(long id, String firstName, String lastName, String username, String email, String title, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.title = title;
        this.password = password;
    }

    public User(){

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getUsername().equals(user.getUsername()) && getEmail().equals(user.getEmail()) && getTitle().equals(user.getTitle()) && getPassword().equals(user.getPassword()) && kolegijList.equals(user.kolegijList) && getAuthorities().equals(user.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getUsername(), getEmail(), getTitle(), getPassword(), kolegijList, getAuthorities());
    }
}
