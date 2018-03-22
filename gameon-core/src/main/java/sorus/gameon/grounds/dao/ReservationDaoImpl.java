package sorus.gameon.grounds.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sorus.gameon.grounds.entities.Reservation;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public class ReservationDaoImpl implements ReservationDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Reservation> list(long groundId, LocalDateTime start, LocalDateTime end) {
        TypedQuery<Reservation> query = em.createNamedQuery(Reservation.GROUND, Reservation.class);
        return query.setParameter("groundId", groundId).setParameter("start", start).setParameter("end", end)
                .getResultList();
    }

    @Override
    public Reservation find(long id) {
        return em.find(Reservation.class, id);
    }

    @Override
    public Reservation delete(long id) {
        Reservation reservation = em.getReference(Reservation.class, id);
        em.remove(reservation);
        return reservation;
    }

    @Override
    public Reservation create(Reservation reservation) {
        return em.merge(reservation);
    }

    @Override
    public Reservation update(long id, Reservation reservation) {
        Reservation oldReservation = find(id);

        oldReservation.setStartTime(reservation.getStartTime());
        oldReservation.setEndTime(reservation.getEndTime());
        return em.merge(oldReservation);
    }
}
