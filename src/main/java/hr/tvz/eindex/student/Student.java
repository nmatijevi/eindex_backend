package hr.tvz.eindex.student;


import hr.tvz.eindex.kolegij.Kolegij;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Student")
public class Student implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

     @ManyToMany(targetEntity = Kolegij.class)
     @JoinTable(
             name = "StudentKolegij",
             joinColumns = { @JoinColumn(name = "studentId") },
             inverseJoinColumns = { @JoinColumn(name = "kolegijId") }
     )
     private List<Kolegij> kolegijList;



    public Student(long id, String firstName, String lastName, String email, String title) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
    }

    public Student(){

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId() == student.getId() && getFirstName().equals(student.getFirstName()) && getLastName().equals(student.getLastName()) && getEmail().equals(student.getEmail()) && getTitle().equals(student.getTitle()) && kolegijList.equals(student.kolegijList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getTitle(), kolegijList);
    }
}
