package rs.rnk.tasks.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import rs.rnk.tasks.rest.model.User;
import rs.rnk.tasks.rest.service.UserService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userById(@PathParam("id") int id) {
        User user = userService.findById(id);
        return Response.ok(user).build();
    }

}
