package hr.tvz.eindex.autohority;


import hr.tvz.eindex.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id")}
    )
    private List<User> users;

    public Authority(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Authority() {

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}