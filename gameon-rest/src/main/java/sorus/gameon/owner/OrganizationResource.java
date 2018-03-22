package sorus.gameon.owner;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import sorus.gameon.owner.org.Organization;
import sorus.gameon.owner.org.OrganizationService;

/**
 * REST end-point for the Organization resource.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Path("/orgs")
public class OrganizationResource {

    private static final String DEFAULT_PAGE_SIZE = "20";

    private static final String DEFAULT_PAGE_NUMBER = "1";

    @Inject
    private OrganizationService service;

    @Inject
    private Logger logger;

    /**
     * @return List of all organizations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Organization> list(@DefaultValue(DEFAULT_PAGE_SIZE) @QueryParam("page-size") int pageSize,
            @DefaultValue(DEFAULT_PAGE_NUMBER) @QueryParam("page-number") int pageNumber) {
        logger.info("OrganizationResource.list(page size: %d, page number: %d)", pageSize, pageNumber);
        return service.list(pageSize, pageNumber);
    }

    /**
     * @param id
     *            Primary key of the organization
     * @return Organization with the id, null if organization does not exist
     */
    @GET
    @Path("/{org-id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Organization find(@PathParam("org-id") final long id) {
        logger.info("OrganizationResource.find(%d)", id);
        return service.find(id);
    }

    /**
     * REST end-point to create a new organization.
     *
     * @param org
     *            Organization to be created.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Organization create(final Organization org) {
        logger.info("Creating new organization: %s", org);
        return service.create(org);
    }

    /**
     * REST end-point to delete an organization.
     * <p>
     * The record is not physically deleted from the database, only the status
     * is updated to DELETED.
     * </p>
     *
     * @param id
     *            organization id
     * @return Status cod 204 No Content
     */
    @DELETE
    @Path("/{org-id:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("org-id") final long id) {
        logger.info("Deleting organization with id: %d", id);
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}
