package rs.rnk.tasks.rest.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {


    @Override
    public Response toResponse(Throwable throwable) {
        var appError = new AppError();

        appError.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        appError.setMessage("Something went wrong with the server. Please try later or contact developer at dev@dev.com");
        var timestamp = new Timestamp(new Date().getTime());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        appError.setTimestamp(format.format(new Date(timestamp.getTime())));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(appError)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
