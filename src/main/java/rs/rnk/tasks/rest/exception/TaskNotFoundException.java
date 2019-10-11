package rs.rnk.tasks.rest.exception;

public class TaskNotFoundException extends AppException {

    private static final String MESSAGE = "Task with id %d was not found for user with id %d.";
    private static final int ERROR_CODE = 7002;

    public TaskNotFoundException(int taskId, String httpMethod, String endpoint, int httpStatus, int userId) {
        super(String.format(MESSAGE, taskId, userId), httpMethod, endpoint, 0, httpStatus);
        this.setCode(ERROR_CODE);
    }
}
