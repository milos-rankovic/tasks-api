package rs.rnk.tasks.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.rnk.tasks.rest.model.Task;
import rs.rnk.tasks.rest.model.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.*;


@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager entityManager;

    public User findById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

}
