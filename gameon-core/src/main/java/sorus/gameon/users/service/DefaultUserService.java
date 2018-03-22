package sorus.gameon.users.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sorus.gameon.users.dao.UserDao;
import sorus.gameon.users.entities.User;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Stateless
public class DefaultUserService implements UserService {

    @Inject
    private UserDao dao;

    @Override
    public List<User> list() {
        return dao.list();
    }

    @Override
    public User find(long id) {
        return dao.find(id);
    }

    @Override
    public User add(User user) {
        if (!existsFbUser(user.getFacebookId()))
            return dao.add(user);
        return null;
    }

    private boolean existsFbUser(String facebookId) {
        return dao.findByFacebookId(facebookId).size() > 0;
    }

    @Override
    public long count() {
        return dao.count();
    }
}
