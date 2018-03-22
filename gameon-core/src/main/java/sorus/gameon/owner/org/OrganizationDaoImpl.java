package sorus.gameon.owner.org;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Class to implement relational database version of the OrganizationDao
 * interface.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public class OrganizationDaoImpl implements OrganizationDao {

    @Inject
    private EntityManager em;

    // TODO - implement pagination
    @Override
    public final List<Organization> list(int pageSize, int pageNumber) {
        TypedQuery<Organization> query = em.createNamedQuery("organization.all", Organization.class);
        return query.getResultList();
    }

    @Override
    public final Organization findById(final long id) {
        TypedQuery<Organization> query = em.createNamedQuery("organization.id", Organization.class);
        return query.setParameter("id", id).getSingleResult();
    }

    @Override
    public Organization create(Organization org) {
        return em.merge(org);
    }

    @Override
    public Organization update(Organization org) {
        return em.merge(org);
    }
}
