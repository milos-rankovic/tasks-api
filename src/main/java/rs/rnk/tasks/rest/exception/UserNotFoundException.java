package rs.rnk.tasks.rest.exception;

public class UserNotFoundException extends AppException {

    private static final String MESSAGE = "User with id %d was not found.";
    private static final int ERROR_CODE = 7001;

    public UserNotFoundException(int userId, String httpMethod, String endpoint, int httpStatus) {
        super(String.format(MESSAGE, userId), httpMethod, endpoint, 0, httpStatus);
        this.setCode(ERROR_CODE);
    }
}
