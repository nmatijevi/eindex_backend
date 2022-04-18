package hr.tvz.eindex.kolegij;

import hr.tvz.eindex.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Kolegij")
public class Kolegij {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;


    @ManyToMany(targetEntity = User.class, mappedBy = "kolegijList")
    private Set<User> userList;



    public Kolegij(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Kolegij() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kolegij)) return false;
        Kolegij kolegij = (Kolegij) o;
        return getId() == kolegij.getId() && getName().equals(kolegij.getName()) && userList.equals(kolegij.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), userList);
    }
}
