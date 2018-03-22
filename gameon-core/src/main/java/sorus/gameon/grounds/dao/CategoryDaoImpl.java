package sorus.gameon.grounds.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sorus.gameon.grounds.entities.Category;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
public class CategoryDaoImpl implements CategoryDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Category> list() {
        TypedQuery<Category> query = em.createNamedQuery(Category.ALL, Category.class);
        return query.getResultList();
    }

    @Override
    public Category create(Category category) {
        return em.merge(category);
    }
}
