package sorus.gameon.core;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;

/**
 * Service that runs at startup and reindexes everything.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Startup
@Singleton
public class LuceneIndexer {

    @Inject
    private EntityManager em;

    @Inject
    private Logger logger;

    @PostConstruct
    public void startup() {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            logger.warn("LuceneIndexer.startup - error occured - %s.", e);
            // TODO - Need better exception handling
        }
    }
}
