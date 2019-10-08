package rs.rnk.tasks.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.rnk.tasks.rest.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
