package sorus.gameon.users.service;

import java.util.List;

import sorus.gameon.users.entities.User;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface UserService {

    User add(User user);

    List<User> list();

    User find(long id);

    long count();
}
