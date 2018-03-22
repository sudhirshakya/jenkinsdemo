package sorus.gameon.grounds.dao;

import java.util.List;

import sorus.gameon.grounds.entities.Category;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface CategoryDao {

    List<Category> list();

    Category create(Category category);

}
