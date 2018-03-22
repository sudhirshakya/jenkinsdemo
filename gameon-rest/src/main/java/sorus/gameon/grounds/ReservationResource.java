package sorus.gameon.grounds;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

import sorus.gameon.grounds.entities.Reservation;
import sorus.gameon.grounds.service.ReservationService;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Path("/grounds/{groundId:\\d+}/reservations")
public class ReservationResource {

    @Inject
    private Logger logger;

    @Inject
    private ReservationService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> list(@PathParam("groundId") long groundId) {
        logger.info("Returning list of reservations for ground: {}", groundId);

        return service.list(groundId);
    }

    @DELETE
    @Path("/{id:\\d+}")
    public Response delete(@PathParam("groundId") long groundId, @PathParam("id") long id) {
        logger.info("Deleting reservation for ground(%d), %d", groundId, id);

        service.delete(groundId, id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("groundId") long groundId, Reservation reservation, @Context UriInfo info) {
        logger.info("Creating reservation for ground ({})", groundId);

        Reservation newReservation = service.create(groundId, reservation);
        if (newReservation != null) {
            URI uri = info.getAbsolutePathBuilder().path("/" + newReservation.getId()).build();
            return Response.created(uri).build();
        } else {
            // TODO - if new reservation is not created, need to return error
            // status.
            return Response.noContent().build();
        }
    }

    @PUT
    @Path("/{id:\\d+}")
    public void update(@PathParam("groundId") long groundId, @PathParam("id") long id, Reservation reservation) {
        logger.info("Updating reservation for ground (%d)", groundId);

        service.update(groundId, id, reservation);

    }
}
