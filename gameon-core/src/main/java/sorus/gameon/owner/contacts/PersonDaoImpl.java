/**
 * 
 */
package sorus.gameon.owner.contacts;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public class PersonDaoImpl implements PersonDao {

    @Inject
    private EntityManager em;

    /**
     * @see sorus.gameon.owner.contacts.PersonDao#list(long)
     */
    @Override
    public List<Person> list(long orgId) {
        TypedQuery<Person> query = em.createNamedQuery("person.org", Person.class).setParameter("orgId", orgId);
        return query.getResultList();
    }

}
