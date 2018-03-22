package sorus.gameon.grounds.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Data
@Entity
@Table(name = "categories", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@NamedQueries({ @NamedQuery(name = Category.ALL, query = "select c from Category c") })
public class Category {

    private static final String NAMESPACE = "gameon.category.";

    public static final String ALL = NAMESPACE + "all";

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "catId")
    @TableGenerator(name = "catId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
    private long id;

    @NotNull
    private String name;
}
