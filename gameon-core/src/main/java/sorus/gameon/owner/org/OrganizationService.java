package sorus.gameon.owner.org;

import java.util.List;

/**
 * Interface declaring the methods for the Organization service.
 *
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface OrganizationService {

    /**
     * Method to return list of all organizations in the database.
     *
     * @return list of students
     */
    List<Organization> list(int pageSize, int pageNumber);

    /**
     * Method to return single organization object identified by its primary
     * key.
     *
     * @param id
     *            Primary key
     * @return single Organization object
     */
    Organization find(long id);

    /**
     * Add a new organization and return it with primary key set.
     * 
     * @param org
     *            Organization to be created.
     * @return Organization with primary key set.
     */
    Organization create(Organization org);

    /**
     * Delete the organization. It does not physically delete the organization;
     * just sets the status to DELETED.
     * 
     * @param id
     *            organization id
     * @return true if it was successfully deleted, false otherwise
     */
    boolean delete(long id);

    /**
     * Updates the organization.
     * 
     * @param changes
     * @return the updated organization
     */
    Organization update(long id, Organization changes);
}
