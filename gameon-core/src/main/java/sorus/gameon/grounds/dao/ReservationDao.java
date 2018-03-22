package sorus.gameon.grounds.dao;

import java.time.LocalDateTime;
import java.util.List;

import sorus.gameon.grounds.entities.Reservation;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface ReservationDao {

    List<Reservation> list(long groundId, LocalDateTime start, LocalDateTime end);

    Reservation find(long id);

    Reservation delete(long id);

    Reservation create(Reservation reservation);

    Reservation update(long id, Reservation reservation);
}
