package rs.rnk.tasks.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.Timestamp;
import java.util.Date;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    private static int WEB_APP_EXCEPTION_CODE = 7009;

    @Override
    public Response toResponse(WebApplicationException e) {
        var appError = new AppError();

        appError.setCode(WEB_APP_EXCEPTION_CODE);
        appError.setMessage(e.getMessage());
        var timestamp = new Timestamp(new Date().getTime());
        appError.setTimestamp(timestamp.toString());
        return Response.status(e.getResponse().getStatus()).entity(appError)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
