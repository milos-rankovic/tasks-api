package rs.rnk.tasks.rest.exception;

public class LoginException extends AppException {

    private static final String WRONG_CREDENTIALS_MESSAGE = "Wrong username or password.";
    private static final String NO_AUTH_HEADER_MESSAGE = "You have to provide credentials in the authorization header.";
    public static final int WRONG_CREDENTIALS = 9001;
    public static final int NO_AUTH_HEADER = 9002;

    public LoginException(String httpMethod, String endpoint, int httpStatus, int errorCode) {
        super("", httpMethod, endpoint, errorCode, httpStatus);

        if (errorCode == WRONG_CREDENTIALS)
            setMessage(WRONG_CREDENTIALS_MESSAGE);
        else if (errorCode == NO_AUTH_HEADER)
            setMessage(NO_AUTH_HEADER_MESSAGE);
    }

}
