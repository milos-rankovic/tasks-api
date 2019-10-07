package rs.rnk.tasks.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
        Response response = Response.ok("Hello World").build();

        return response;
    }

}
