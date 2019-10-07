package rs.rnk.tasks.rest.service;

import org.springframework.stereotype.Service;
import rs.rnk.tasks.rest.model.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private static List<User> dbUsers = new ArrayList<>();

    static {
        var birthDateCal = Calendar.getInstance();
        birthDateCal.set(1990, Calendar.AUGUST, 12);
        var user1 = new User(1, "user1", "user123", "user1@test.com", "First Last 1", birthDateCal.getTime());
        birthDateCal.add(Calendar.YEAR, 2);
        var user2 = new User(2, "user2", "user123", "user1@test.com", "First Last 2", birthDateCal.getTime());
        birthDateCal.add(Calendar.YEAR, -4);
        var user3 = new User(3, "user3", "user123", "user1@test.com", "First Last 3", birthDateCal.getTime());

        Collections.addAll(dbUsers, user1, user2, user3);
    }

    public User findById(int id) {
        for(var user : dbUsers) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}
