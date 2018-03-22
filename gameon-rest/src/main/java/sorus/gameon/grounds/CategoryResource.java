package sorus.gameon.grounds;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

import sorus.gameon.grounds.entities.Category;
import sorus.gameon.grounds.service.CategoryService;

/**
 * REST end-point for the Ground Category resource.
 * <p>
 * The resource will be used to CRUD ground types. Types might include - futsal,
 * cricket, laser-tag etc.
 * </p>
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Path("/grounds/categories")
public class CategoryResource {

    @Inject
    private CategoryService service;

    @Inject
    private Logger logger;

    /**
     * @return List of all ground categories.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> list() {
        logger.info("List all ground categories");
        return service.list();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Category category, @Context UriInfo info) {
        category = service.create(category);

        URI uri = info.getAbsolutePathBuilder().path("/" + category.getId()).build();
        return Response.created(uri).build();
    }
}
