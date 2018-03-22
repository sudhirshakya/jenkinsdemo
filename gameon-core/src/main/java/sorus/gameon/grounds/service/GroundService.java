package sorus.gameon.grounds.service;

import java.io.InputStream;
import java.util.List;

import sorus.gameon.grounds.entities.Ground;
import sorus.gameon.grounds.entities.Image;

/**
 * 
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface GroundService {

    List<Ground> list(long since);

    Ground find(long id);

    Ground create(Ground ground);

    Image addImage(long id, InputStream is, String filename);

    List<Ground> search(String searchTerms);
}
