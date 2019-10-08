package rs.rnk.tasks.rest.model;

import java.util.Date;

public class Task {

    private int id;
    private String title;
    private String description;
    private User user;
    private Date date;
    private Date time;
    private boolean done;

    public Task(String title, String description, User user, Date date, Date time, boolean done) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.date = date;
        this.time = time;
        this.done = done;
    }

    public Task(int id, String title, String description, User user, Date date, Date time, boolean done, boolean allDay) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
