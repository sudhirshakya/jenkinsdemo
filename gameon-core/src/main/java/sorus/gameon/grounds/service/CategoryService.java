package sorus.gameon.grounds.service;

import java.util.List;

import sorus.gameon.grounds.entities.Category;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public interface CategoryService {

    List<Category> list();

    Category create(Category category);

}
