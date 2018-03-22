package sorus.gameon.grounds.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import sorus.gameon.grounds.entities.Ground;
import sorus.gameon.grounds.entities.Image;

public class GroundDaoImpl implements GroundDao {

    @Inject
    private EntityManager em;

    @Override
    public final List<Ground> list(long since) {
        TypedQuery<Ground> query = em.createNamedQuery(Ground.SINCE, Ground.class);
        return query.setParameter("since", since).getResultList();
    }

    @Override
    public Ground findById(long id) {
        return em.find(Ground.class, id);
    }

    @Override
    public Ground create(Ground ground) {
        return em.merge(ground);
    }

    @Override
    public Ground update(Ground ground) {
        return em.merge(ground);
    }

    @Override
    public Image update(Image image) {
        return em.merge(image);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ground> search(String searchTerms) {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
        QueryBuilder qb = ftem.getSearchFactory().buildQueryBuilder().forEntity(Ground.class).get();
        Query query = qb.keyword().onFields("name").matching(searchTerms).createQuery();

        javax.persistence.Query jpaQuery = ftem.createFullTextQuery(query, Ground.class);

        return (List<Ground>) jpaQuery.getResultList();
    }
}
