package rs.rnk.tasks.rest.resource;

import rs.rnk.tasks.rest.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {
        var birthDateCal = Calendar.getInstance();
        birthDateCal.set(1990, Calendar.AUGUST, 12);

        var user = new User("user", "user123", "user@test.com", "First Last", birthDateCal.getTime());
        Response response = Response.ok(user).build();

        return response;
    }

}
