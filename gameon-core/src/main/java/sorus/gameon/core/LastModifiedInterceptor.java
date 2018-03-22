package sorus.gameon.core;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * The <code>WriterInterceptor</code> will add a custom X-Last-Modified header.
 * The header stores the last modified value in milliseconds.
 * 
 * Use the <code>LastModified</code> marker annotation to mark any class or
 * method that needs this header.
 * 
 * @see LastModified
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Provider
@LastModified
public class LastModifiedInterceptor implements WriterInterceptor {

    private static final String LAST_MODIFIED_HEADER = "X-Last-Modified";

    @Override
    @SuppressWarnings("unchecked")
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {

        // Get the last updated entity
        List<AuditedEntity> entities = (List<AuditedEntity>) context.getEntity();

        long lastModifiedTime = 0L;
        if (entities.size() > 0) {
            AuditedEntity lastUpdated = Collections.max(entities,
                    (o1, o2) -> (Long.compare(o1.getLastModified(), o2.getLastModified())));

            lastModifiedTime = lastUpdated.getLastModified();
        }

        // Set the last updated header
        MultivaluedMap<String, Object> headers = context.getHeaders();
        headers.add(LAST_MODIFIED_HEADER, lastModifiedTime);

        context.proceed();
    }
}
