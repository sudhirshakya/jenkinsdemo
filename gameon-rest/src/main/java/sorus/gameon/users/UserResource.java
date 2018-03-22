package sorus.gameon.users;

import io.swagger.annotations.Api;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import sorus.gameon.users.entities.User;
import sorus.gameon.users.service.UserService;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Path("/users")
@Api("users")
public class UserResource {

    @Inject
    private UserService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        return service.list();
    }

    @GET
    @Path("/{userid:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("userid") long id) {
        return service.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user, @Context UriInfo info) {
        user = service.add(user);
        if (user != null) {
            URI uri = info.getAbsolutePathBuilder().path("/" + user.getId()).build();
            return Response.created(uri).build();
        } else {
            return Response.noContent().build();
        }
    }
}
