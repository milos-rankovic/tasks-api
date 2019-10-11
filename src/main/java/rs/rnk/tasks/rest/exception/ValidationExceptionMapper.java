package rs.rnk.tasks.rest.exception;

import rs.rnk.tasks.rest.model.Task;
import rs.rnk.tasks.rest.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.Timestamp;
import java.util.Date;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final int USER_VALIDATION_ERROR_CODE = 8001;
    private static final int TASK_VALIDATION_ERROR_CODE = 8002;
    private static final String USER_VALIDATION_ERROR_MESSAGE = "User is invalid [%s]";
    private static final String TASK_VALIDATION_ERROR_MESSAGE = "Task is invalid [%s]";

    @Override
    public Response toResponse(ConstraintViolationException e) {
        var appError = new AppError();

        ConstraintViolation constraintViolation = e.getConstraintViolations().iterator().next();
        Object leafBean = constraintViolation.getLeafBean();
        String message = constraintViolation.getMessage();
        if (leafBean instanceof User) {
            appError.setCode(USER_VALIDATION_ERROR_CODE);
            appError.setMessage(String.format(USER_VALIDATION_ERROR_MESSAGE, message));
        } else if (leafBean instanceof Task) {
            appError.setCode(TASK_VALIDATION_ERROR_CODE);
            appError.setMessage(String.format(TASK_VALIDATION_ERROR_MESSAGE, message));
        }
        var timestamp = new Timestamp(new Date().getTime());
        appError.setTimestamp(timestamp.toString());

        return Response.status(Response.Status.BAD_REQUEST).entity(appError).build();
    }
}
