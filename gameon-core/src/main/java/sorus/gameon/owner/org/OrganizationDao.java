package sorus.gameon.owner.org;

import java.util.List;

/**
 * DAO interface for Organization objects.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface OrganizationDao {

    /**
     * Method to list all the students in the database.
     * 
     * @return list of students
     */
    List<Organization> list(int pageSize, int pageNumber);

    /**
     * Method to get the record of a single student using the student id.
     * 
     * @param id
     *            Primary key of the record
     * @return single Student object
     */
    Organization findById(long id);

    /**
     * Persist the new organization to database.
     * 
     * @param org
     *            New organization to be persisted to database.
     * @return the persisted organization
     */
    Organization create(Organization org);

    /**
     * Persist the updated organization to database
     * 
     * @param org
     *            Updated organization
     * @return Updated organization after persistence
     */
    Organization update(Organization org);
}
