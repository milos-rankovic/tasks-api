package rs.rnk.tasks.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
    @NotNull(message = "you must provide username")
    private String username;
    @NotNull(message = "you must provide password")
    private String password;
    @NotNull(message = "you must provide email")
    private String email;
    @NotNull(message = "you must provide name")
    private String name;
    @NotNull(message = "you must provide birth date")
    private Date birthDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Task> tasks;

    public User() {
        tasks = new ArrayList<>();
    }

    public User(String username, String password, String email, String name, Date birthDate) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(int id, String username, String password, String email, String name, Date birthDate) {
        this(username, password, email, name, birthDate);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
