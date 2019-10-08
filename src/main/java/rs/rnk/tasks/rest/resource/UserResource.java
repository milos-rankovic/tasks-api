package rs.rnk.tasks.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import rs.rnk.tasks.rest.exception.UserNotFoundException;
import rs.rnk.tasks.rest.model.User;
import rs.rnk.tasks.rest.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userById(@PathParam("id") int id, @Context UriInfo uriInfo) throws UserNotFoundException {
        User user = userService.findById(id);

        if(user == null) throw new UserNotFoundException(id, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        return Response.ok(user).build();
    }

}
