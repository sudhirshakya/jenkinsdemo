package sorus.gameon.users.dao;

import java.util.List;

import sorus.gameon.users.entities.User;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface UserDao {

    User add(User user);

    List<User> findByFacebookId(String facebookId);

    List<User> list();

    User find(long id);

    long count();

}
