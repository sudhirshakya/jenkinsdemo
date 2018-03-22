package sorus.gameon.core;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Dependent
public class Resources {

    /**
     * The JPA Entity Manager created from persistence.xml.
     */
    @PersistenceContext
    @Produces
    private EntityManager em;

    /**
     * Method to inject Logger in different classes.
     *
     * @param ip
     *            InjectionPoint
     * @return Logger to be injected at InjectionPoint
     */
    @Produces
    public final Logger createLogger(final InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }

}
