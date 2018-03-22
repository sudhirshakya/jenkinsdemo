package sorus.gameon.owner;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import sorus.gameon.owner.contacts.Person;
import sorus.gameon.owner.contacts.PersonService;

/**
 * REST end-point for the contacts within the organization.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Path("/orgs/{orgId:\\d+}/people")
public class PersonResource {

    @Inject
    private Logger logger;

    @Inject
    private PersonService service;

    /**
     * 
     * FIXME - logger is not printing the arguments.
     * 
     * @param orgId
     *            ID of the organization whose contacts is being listed
     * @return list of active contacts in the organization
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> list(@PathParam("orgId") long orgId) {
        logger.info("List of org (%d) contacts", orgId);

        return service.list(orgId);
    }

}
