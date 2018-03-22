package sorus.gameon.core;

import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Base abstract class to extend.
 * <p>
 * Adds Last Modified date-time field.
 * </p>
 * <ul>
 * <li>TODO - Need to consider whether CreatedDate, CreatedBy,
 * UpdatedDate,UpdatedBy fields will be more appropriate.</li>
 * <li>TODO - Is this a Trait or an abstract class?</li>
 * </ul>
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Data
@MappedSuperclass
public abstract class AuditedEntity {

    private long lastModified;
}
