package sorus.gameon.owner.org;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import sorus.gameon.common.entities.Address;
import sorus.gameon.common.entities.Location;
import sorus.gameon.grounds.entities.Ground;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * DTO for Organization objects.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Data
@NamedQueries({
        @NamedQuery(name = "organization.all", query = "select o from Organization o where o.status <> sorus.gameon.owner.org.OrganizationStatus.DELETED"),
        @NamedQuery(name = "organization.id", query = "select o from Organization o where o.id = :id") })
@Entity
@Table(name = "organizations")
public class Organization {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orgId")
    @TableGenerator(name = "orgId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
    private long id;

    /**
     * Official name of the organization.
     */
    @NotNull
    private String name;

    /**
     * Address used in the registration of the organization.
     */
    @Embedded
    private Address address;

    /**
     * GPS location (longitude, latitude) used to plot in a map.
     */
    @Embedded
    private Location location;

    /**
     * Status of the organization - new, inactive, deleted...
     */
    private OrganizationStatus status;

    /**
     * List of grounds.
     * <ul>
     * <li>TODO - Need to fix naming.</li>
     * <li>FIXME - Need to confirm if Jackson annotation is needed to solve this
     * problem here.</li>
     * <li>FIXME - Fetch type set to eager to supress Hibernate session closed
     * error.</li>
     * </ul>
     */
    @OneToMany(mappedBy = "org", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Ground> grounds;

    @Transient
    private String addressSingleLine;

    @PostLoad
    public void postLoad() {
        if (this.address != null)
            this.addressSingleLine = this.address.toString();
    }
}
