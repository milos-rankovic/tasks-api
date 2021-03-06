package rs.rnk.tasks.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.Timestamp;
import java.util.Date;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static int UNKNOWN_ERROR = 7010;

    @Override
    public Response toResponse(Exception e) {
        var appError = new AppError();
        appError.setCode(UNKNOWN_ERROR);
        appError.setMessage("Something went wrong. Please try again later or contact us: dev@dev.com");
        var timestamp = new Timestamp(new Date().getTime());
        appError.setTimestamp(timestamp.toString());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(appError)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
