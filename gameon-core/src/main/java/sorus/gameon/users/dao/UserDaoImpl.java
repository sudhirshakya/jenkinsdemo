package sorus.gameon.users.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import sorus.gameon.users.entities.User;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public class UserDaoImpl implements UserDao {

    @Inject
    private EntityManager em;

    @Override
    public List<User> list() {
        return em.createNamedQuery(User.ALL, User.class).getResultList();
    }

    @Override
    public User find(long id) {
        return em.find(User.class, id);
    }

    @Override
    public long count() {
        return em.createNamedQuery(User.COUNT, Long.class).getSingleResult();
    }

    @Override
    public User add(User user) {
        return em.merge(user);
    }

    @Override
    public List<User> findByFacebookId(String facebookId) {
        return em.createNamedQuery(User.FACEBOOK, User.class).setParameter("facebookId", facebookId).getResultList();
    }
}
