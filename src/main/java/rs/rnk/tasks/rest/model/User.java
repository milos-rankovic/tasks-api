package rs.rnk.tasks.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class User {

    private int id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String email;
    private String name;
    @JsonIgnore
    private Date birthDate;

    private List<Task> tasks;

    public User(String username, String password, String email, String name, Date birthDate, List<Task> tasks) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.tasks = tasks;
    }

    public User(int id, String username, String password, String email, String name, Date birthDate, List<Task> tasks) {
        this(username, password, email, name, birthDate, tasks);
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
