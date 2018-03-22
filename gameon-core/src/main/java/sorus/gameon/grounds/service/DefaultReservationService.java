package sorus.gameon.grounds.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import sorus.gameon.grounds.dao.ReservationDao;
import sorus.gameon.grounds.entities.Reservation;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Stateless
public class DefaultReservationService implements ReservationService {

    @Inject
    private Logger logger;

    @Inject
    private ReservationDao dao;

    @Inject
    private GroundService service;

    /*
     * (non-Javadoc)
     * 
     * @see sorus.gameon.grounds.reservations.ReservationService#list(long)
     */
    @Override
    public List<Reservation> list(long groundId) {
        logger.debug("Listing all reservations for ground (%d)", groundId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
        LocalDateTime end = start.plusDays(30);
        return dao.list(groundId, start, end);
    }

    @Override
    public void delete(long groundId, long id) {

        logger.debug("Deleting reservation for ground (%) with id:%d", groundId, id);
        dao.delete(id);
    }

    @Override
    public Reservation create(long groundId, Reservation reservation) {
        logger.debug("Creating new reservation for ground {}", groundId);

        reservation.setGround(service.find(groundId));
        reservation.setLastModified(System.currentTimeMillis());

        return dao.create(reservation);

    }

    @Override
    public void update(long groundId, long id, Reservation reservation) {
        logger.debug("Updating reservation");

    }

}
