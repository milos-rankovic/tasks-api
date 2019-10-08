package rs.rnk.tasks.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

    @Override
    public Response toResponse(AppException e) {
        var appError = new AppError();

        appError.setCode(e.getCode());
        appError.setMessage(e.getMessage());
        appError.setTimestamp(e.getTimestampFormatted());

        return Response.status(e.getHttpStatus()).entity(appError).type(MediaType.APPLICATION_JSON).build();
    }
}
