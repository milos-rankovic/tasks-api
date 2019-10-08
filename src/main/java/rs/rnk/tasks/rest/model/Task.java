package rs.rnk.tasks.rest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Date date;
    private Time time;
    @Column(name = "is_done")
    private boolean done;

    public Task() {}

    public Task(int id, String title, String description, Date date, Time time, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.done = done;
    }

    public Task(String title, String description, User user, Date date, Time time, boolean done) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.date = date;
        this.time = time;
        this.done = done;
    }

    public Task(int id, String title, String description, User user, Date date, Time time, boolean done, boolean allDay) {
        this(title, description, user, date, time, done);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
