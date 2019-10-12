package rs.rnk.tasks.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import rs.rnk.tasks.rest.exception.LoginException;
import rs.rnk.tasks.rest.exception.UserNotFoundException;
import rs.rnk.tasks.rest.model.LoginInfo;
import rs.rnk.tasks.rest.model.User;
import rs.rnk.tasks.rest.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Context
    private UriInfo uriInfo;


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@HeaderParam("Authorization") String authHeader, @PathParam("id") int id) throws UserNotFoundException, LoginException {
        User user = userService.findByIdAndFetchTasks(id);
        if (user == null)
            throw new UserNotFoundException(id, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());

        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid User user) {
        user.setId(0);
        int createdUserId = userService.register(user);
        return Response.created(UriBuilder.fromPath(uriInfo.getAbsolutePath().toString()).path(Integer.toString(createdUserId)).build()).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@HeaderParam("Authorization") String authHeader, @PathParam("id") int id) throws UserNotFoundException, LoginException {
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException(id, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        }
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        userService.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id, @Valid User user, @HeaderParam("Authorization") String authHeader) throws UserNotFoundException, LoginException {
        User existingUser = userService.findById(id);
        if (existingUser == null)
            throw new UserNotFoundException(id, HttpMethod.PUT, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(existingUser, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        user.setId(id);
        User updatedUser = userService.update(user);
        return Response.status(Response.Status.OK).entity(updatedUser).build();
    }

    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response patch(@PathParam("id") int id, User user, @HeaderParam("Authorization") String authHeader) throws UserNotFoundException, LoginException {
        User existingUser = userService.findById(id);
        if (existingUser == null)
            throw new UserNotFoundException(id, HttpMethod.PATCH, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());

        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(existingUser, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }

        if (user.getBirthDate() != null) {
            existingUser.setBirthDate(user.getBirthDate());
        }

        User updatedUser = userService.update(existingUser);
        return Response.ok(updatedUser).build();
    }

}
