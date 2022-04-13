package hr.tvz.eindex.user;


import hr.tvz.eindex.autohority.Authority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserCommand {


    @NotNull(message = "Must not be empty")
    private long id;

    @NotBlank(message = "Must not be empty")
    private String firstName;

    @NotBlank(message = "Must not be empty")
    private String lastName;

    @NotBlank(message = "Must not be empty")
    private String username;

    @NotBlank(message = "Must not be empty")
    private String email;

    @NotBlank(message = "Must not be empty")
    private String title;

    @NotBlank(message = "Must not be empty")
    private String password;

    private Set<Authority> authority;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Authority> authority) {
        this.authority = authority;
    }
}
