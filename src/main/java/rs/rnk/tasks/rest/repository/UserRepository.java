package rs.rnk.tasks.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.rnk.tasks.rest.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
