package sorus.gameon.owner.org;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;

/**
 * The default class implementing the OrganizationService.
 *
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Stateless
public class DefaultOrganizationService implements OrganizationService {

    @Inject
    private Logger logger;

    @Inject
    private OrganizationDao dao;

    @Override
    public List<Organization> list(int pageSize, int pageNumber) {
        logger.debug("List of all organizations");
        return dao.list(pageSize, pageNumber);
    }

    @Override
    public Organization find(final long id) {
        logger.debug("Find single organization using id (%d)", id);
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Organization create(Organization org) {
        logger.debug("Creating new organization %s", org);

        org.setStatus(OrganizationStatus.CREATED);
        return dao.create(org);
    }

    @Override
    public boolean delete(long id) {
        logger.debug("Deleting (logically) organization with id: ", id);

        Organization org = new Organization();
        org.setStatus(OrganizationStatus.DELETED);
        update(id, org);

        return true;
    }

    @Override
    public Organization update(long id, Organization updatedOrg) {

        Organization org = find(id);
        org.setStatus(updatedOrg.getStatus());

        return dao.update(org);
    }
}
