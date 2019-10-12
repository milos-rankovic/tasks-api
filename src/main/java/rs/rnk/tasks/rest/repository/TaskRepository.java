package rs.rnk.tasks.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import rs.rnk.tasks.rest.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    public List<Task> findByUser_Id(int userId);

    public Optional<Task> findByIdAndUser_Id(int id, int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.user.id = (:userId)")
    public void deleteByUserId(int userId);

}
