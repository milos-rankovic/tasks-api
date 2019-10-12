package rs.rnk.tasks.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import rs.rnk.tasks.rest.exception.LoginException;
import rs.rnk.tasks.rest.exception.TaskNotFoundException;
import rs.rnk.tasks.rest.exception.UserNotFoundException;
import rs.rnk.tasks.rest.model.LoginInfo;
import rs.rnk.tasks.rest.model.Task;
import rs.rnk.tasks.rest.model.User;
import rs.rnk.tasks.rest.service.TaskService;
import rs.rnk.tasks.rest.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/users/{userId}/tasks")
public class TaskResource {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByUserId(@PathParam("userId") int userId, @HeaderParam("Authorization") String authHeader) throws UserNotFoundException, LoginException {

        User user = userService.findById(userId);

        if (user == null)
            throw new UserNotFoundException(userId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        List<Task> tasks = taskService.findAllByUserId(userId);

        return Response.ok(tasks).build();
    }

    @GET
    @Path("{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("userId") int userId,
            @PathParam("taskId") int taskId,
            @HeaderParam("Authorization") String authHeader) throws UserNotFoundException, TaskNotFoundException, LoginException {
        User user = userService.findById(userId);
        if (user == null)
            throw new UserNotFoundException(userId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        Task task = taskService.findByIdAndUserId(taskId, userId);

        if (task == null)
            throw new TaskNotFoundException(taskId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode(), userId);

        return Response.ok(task).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("userId") int userId, @Valid Task task, @HeaderParam("Authorization") String authHeader) throws UserNotFoundException, LoginException {
        User user = userService.findById(userId);
        if (user == null)
            throw new UserNotFoundException(userId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        task.setUser(user);
        task.setId(0);
        int createdTaskId = taskService.create(task);
        URI locationUri = UriBuilder.fromPath(uriInfo.getAbsolutePath().toString()).path(Integer.toString(createdTaskId)).build();
        return Response.created(locationUri).build();
    }

    @DELETE
    public Response deleteAll(@PathParam("userId") int userId, @HeaderParam("Authorization") String authHeader) throws UserNotFoundException, LoginException {
        User user = userService.findById(userId);

        if (user == null)
            throw new UserNotFoundException(userId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        taskService.deleteAll(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{taskId}")
    public Response delete(@PathParam("userId") int userId, @PathParam("taskId") int taskId, @HeaderParam("Authorization") String authHeader) throws TaskNotFoundException, UserNotFoundException, LoginException {
        User user = userService.findByIdAndFetchTasks(userId);

        if (user == null)
            throw new UserNotFoundException(userId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        if (!user.getTasks().contains(new Task(taskId)))
            throw new TaskNotFoundException(taskId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode(), userId);
        try {
            taskService.deleteById(taskId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new TaskNotFoundException(taskId, HttpMethod.DELETE, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode(), userId);
        }
    }

    @PATCH
    @Path("{taskId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response patch(@PathParam("userId") int userId, @PathParam("taskId") int taskId, Task task, @HeaderParam("Authorization") String authHeader) throws UserNotFoundException, TaskNotFoundException, LoginException {
        User user = userService.findByIdAndFetchTasks(userId);
        if (user == null)
            throw new UserNotFoundException(userId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode());
        var loginInfo = new LoginInfo.Builder(authHeader).build();
        boolean checkLogin = userService.checkLogin(user, loginInfo);
        if (!checkLogin)
            throw new LoginException(HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.UNAUTHORIZED.getStatusCode());
        if (!user.getTasks().contains(new Task(taskId)))
            throw new TaskNotFoundException(taskId, HttpMethod.GET, uriInfo.getAbsolutePath().toString(), Response.Status.NOT_FOUND.getStatusCode(), userId);

        List<Task> tasks = user.getTasks();
        Task existingTask = tasks.get(tasks.indexOf(new Task(taskId)));

        if (task.getTitle() != null)
            existingTask.setTitle(task.getTitle());

        if (task.getDescription() != null)
            existingTask.setDescription(task.getDescription());

        if (task.getDate() != null)
            existingTask.setDate(task.getDate());

        if (task.getTime() != null)
            existingTask.setTime(task.getTime());

        if (task.isDone() != null)
            existingTask.setDone(task.isDone());

        Task updatedTask = taskService.update(existingTask);
        return Response.ok(updatedTask).build();

    }

}
