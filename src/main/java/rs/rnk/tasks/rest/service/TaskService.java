package rs.rnk.tasks.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.rnk.tasks.rest.model.Task;
import rs.rnk.tasks.rest.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllByUserId(int userId) {
        return taskRepository.findByUser_Id(userId);
    }

    public Task findByIdAndUserId(int id, int userId) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUser_Id(id, userId);
        return taskOptional.orElse(null);
    }

    public List<Task> findNotFinishedByUserId(int userId) {
        List<Task> tasks = taskRepository.findByDoneAndUser_Id(false, userId);
        return tasks;
    }

    public int create(Task task) {
        return taskRepository.save(task).getId();
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    public void deleteAll(int userId) {
        taskRepository.deleteByUserId(userId);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

}
