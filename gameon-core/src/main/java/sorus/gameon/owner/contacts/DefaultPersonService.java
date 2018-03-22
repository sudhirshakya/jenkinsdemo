package sorus.gameon.owner.contacts;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Stateless
public class DefaultPersonService implements PersonService {

    @Inject
    private PersonDao dao;

    @Override
    public List<Person> list(long orgId) {

        List<Person> l = dao.list(orgId);
        System.out.println(l);

        return dao.list(orgId);
    }

}
