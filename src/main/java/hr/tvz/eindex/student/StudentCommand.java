package hr.tvz.eindex.student;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentCommand {


    @NotNull(message = "Must not be empty")
    private long id;

    @NotBlank(message = "Must not be empty")
    private String firstName;

    @NotBlank(message = "Must not be empty")
    private String lastName;

    @NotBlank(message = "Must not be empty")
    private String title;

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
}
