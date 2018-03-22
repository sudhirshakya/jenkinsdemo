package sorus.gameon.grounds.dao;

import java.util.List;

import sorus.gameon.grounds.entities.Ground;
import sorus.gameon.grounds.entities.Image;

/**
 * DAO layer for Ground.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface GroundDao {

    /**
     * List all active grounds.
     * 
     * @param since
     * 
     * @return list of all active grounds.
     */
    List<Ground> list(long since);

    Ground findById(long id);

    Ground create(Ground ground);

    Ground update(Ground ground);

    Image update(Image image);

    List<Ground> search(String searchTerms);
}
