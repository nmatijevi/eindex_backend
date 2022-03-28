package hr.tvz.eindex.student;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

@Repository
public class RepositoryClass implements StudentRepository, Serializable {


    
    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> findUserByFirstName(String firstName) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Student> save(Student student) {
        return Optional.empty();
    }
/*
    private final List<User> users = new
            ArrayList<>(
            Arrays.asList(
            new User(1, "Nikola", "Matijevic", "prof1"),
            new User(2, "Ivan", "Kraljić", "prof2"
            )));


    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findUserByFirstName(final String firstName) {
        return users.stream().filter(it -> Objects.equals(it.getFirstName(),firstName)).findAny();
    }

    @Override
    public boolean deleteByFirstName(String firstName) {
        return users.removeIf(v -> v.getFirstName().equals(firstName));
    }

    @Override
    public Optional<User> save(final User user) {
       boolean check = true;
       for(User u: users){
           if(u.getFirstName().equals(user.getFirstName())){
               check = false;
               break;
           }
       }
       if(check){
           users.add(user);
           return Optional.of(user);
       }
       else{
           return Optional.empty();
       }
    }


 */

}
