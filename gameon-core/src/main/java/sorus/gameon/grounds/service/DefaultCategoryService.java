package sorus.gameon.grounds.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import sorus.gameon.grounds.dao.CategoryDao;
import sorus.gameon.grounds.entities.Category;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Stateless
public class DefaultCategoryService implements CategoryService {

    @Inject
    private CategoryDao dao;

    @Inject
    private Logger logger;

    @Override
    public List<Category> list() {
        logger.debug("List of all ground categories");

        return dao.list();
    }

    @Override
    public Category create(Category category) {
        return dao.create(category);
    }

}
