package sorus.gameon.owner.contacts;

import java.util.List;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface PersonDao {

    List<Person> list(long orgId);
}
