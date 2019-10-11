package rs.rnk.tasks.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.rnk.tasks.rest.model.User;
import rs.rnk.tasks.rest.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public int register(User user) {
        return userRepository.save(user).getId();
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User findByIdAndFetchTasks(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        // Call tasks.size() to fetch tasks from db, tasks fetch type: FetchType.LAZY
        userOptional.ifPresent((u) -> u.getTasks().size());
        return userOptional.orElse(null);
    }

}
