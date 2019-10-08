package rs.rnk.tasks.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.rnk.tasks.rest.exception.UserNotFoundException;
import rs.rnk.tasks.rest.model.Task;
import rs.rnk.tasks.rest.model.User;
import rs.rnk.tasks.rest.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.*;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

}
