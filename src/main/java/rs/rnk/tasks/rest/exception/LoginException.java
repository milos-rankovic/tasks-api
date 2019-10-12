package rs.rnk.tasks.rest.exception;

public class LoginException extends AppException {

    private static final String MESSAGE = "Wrong username or password.";
    private static final int ERROR_CODE = 9001;


    public LoginException(String httpMethod, String endpoint, int httpStatus) {
        super(MESSAGE, httpMethod, endpoint, ERROR_CODE, httpStatus);
    }

}
