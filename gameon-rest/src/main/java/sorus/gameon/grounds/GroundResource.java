package sorus.gameon.grounds;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

import sorus.gameon.core.LastModified;
import sorus.gameon.grounds.entities.Ground;
import sorus.gameon.grounds.entities.Image;
import sorus.gameon.grounds.service.GroundService;

/**
 * REST end-point for the Grounds resource.
 * <p>
 * The resource will be used to CRUD futsal grounds and other similar resources.
 * </p>
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Path("/grounds")
@Api("grounds")
public class GroundResource {

    @Inject
    private GroundService service;

    @Inject
    private Logger logger;

    /**
     * @return List of all grounds.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @LastModified
    @ApiOperation(value = "List all grounds", response = Ground.class, responseContainer = "List")
    public List<Ground> list(@Context HttpHeaders headers, @DefaultValue("0") @QueryParam("since") long since) {
        logger.info("GroundResource.list()");

        return service.list(since);
    }

    @GET
    @Path("/{ground-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get single Ground", response = Ground.class)
    public Ground find(@PathParam("ground-id") final long id) {
        logger.info("GroundResource.find(%d)", id);
        return service.find(id);
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ground> search(@QueryParam("q") final String searchTerms) {
        return service.search(searchTerms);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Ground ground, @Context UriInfo info) {
        logger.info("Creating new ground: %s", ground);

        ground = service.create(ground);
        URI uri = info.getAbsolutePathBuilder().path("/" + ground.getId()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{ground-id:\\d+}/images")
    public Response addImage(@PathParam("ground-id") final long id, final InputStream is,
            @DefaultValue("") @QueryParam("name") final String filename) {
        Image image = service.addImage(id, is, filename);

        return Response.created(image.getUrl()).build();
    }
}
