package sorus.gameon.users.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }),
        @UniqueConstraint(columnNames = { "facebookId" }) })
@NamedQueries({ @NamedQuery(name = User.ALL, query = "select u from User u"),
        @NamedQuery(name = User.FACEBOOK, query = "select u from User u where u.facebookId = :facebookId"),
        @NamedQuery(name = User.COUNT, query = "select count(u) from User u") })
@Data
public class User {

    private static final String NAMESPACE = "gameon.user.";

    public static final String FACEBOOK = NAMESPACE + "fb";

    public static final String ALL = NAMESPACE + "all";

    public static final String COUNT = NAMESPACE + "count";

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userId")
    @TableGenerator(name = "userId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
    private long id;

    private String name;

    private String email;

    private String facebookId;
}
