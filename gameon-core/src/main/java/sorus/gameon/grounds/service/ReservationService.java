package sorus.gameon.grounds.service;

import java.util.List;

import sorus.gameon.grounds.entities.Reservation;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface ReservationService {

    List<Reservation> list(long groundId);

    void delete(long groundId, long id);

    Reservation create(long groundId, Reservation reservation);

    void update(long groundId, long id, Reservation reservation);

}
