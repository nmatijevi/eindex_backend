package hr.tvz.eindex.profesor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProfesorCommand {

    @NotNull(message = "Must not be empty")
    private long id;

    @NotBlank(message ="Must not be empty")
    private String firstName;

    @NotBlank(message = "Must not be empty")
    private String lastName;

    @NotBlank(message = "Must not be empty")
    private String email;

    @NotBlank(message = "Must not be empty")
    private String title;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
