package rs.rnk.tasks.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.rnk.tasks.rest.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsernameAndPassword(String username, String password);

}
