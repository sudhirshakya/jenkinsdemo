package sorus.gameon.owner.contacts;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;
import sorus.gameon.owner.org.Organization;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Data
@NamedQueries({ @NamedQuery(name = "person.id", query = "select p from Person p where p.id = :id"),
        @NamedQuery(name = "person.org", query = "select p from Person p where p.org.id = :orgId and p.active = true") })
@Entity
@Table(name = "contacts")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "contactId")
    @TableGenerator(name = "contactId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
    private long id;

    private String firstName;

    private String lastName;

    private String middleName;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization org;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnore
    private Map<String, String> phones;

    private String position;

    private boolean active;

    private boolean preferred;
}
