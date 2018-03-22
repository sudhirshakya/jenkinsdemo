package sorus.gameon.grounds.entities;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import sorus.gameon.common.entities.Address;
import sorus.gameon.common.entities.Location;
import sorus.gameon.core.AuditedEntity;
import sorus.gameon.core.LocalTimeDeserializer;
import sorus.gameon.core.LocalTimeSerializer;
import sorus.gameon.owner.org.Organization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * DTO for futsal grounds.
 * <p>
 * This class handles general information about Futsal grounds - information
 * related to registration, address.
 * </p>
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQueries({
        @NamedQuery(name = Ground.ALL, query = "select g from Ground g where g.status = sorus.gameon.grounds.entities.GroundStatus.ACTIVE"),
        @NamedQuery(name = Ground.SINCE, query = "select g from Ground g where g.status = sorus.gameon.grounds.entities.GroundStatus.ACTIVE and g.lastModified > :since") })
@Entity
@Table(name = "grounds", indexes = { @Index(columnList = "lastModified"), @Index(columnList = "status") })
@Indexed
public class Ground extends AuditedEntity {

    private static final String NAMESPACE = "gameon.ground.";

    public static final String ALL = NAMESPACE + "all";

    public static final String SINCE = NAMESPACE + "since";

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groundId")
    @TableGenerator(name = "groundId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
    private long id;

    /**
     * Name of the ground.
     */
    @Field
    private String name;

    /**
     * Small description of the ground.
     */
    @Field
    private String description;

    /**
     * Address of the ground.
     */
    @Embedded
    @IndexedEmbedded
    private Address address;

    /**
     * GPS location (longitude, latitude) of the ground.
     */
    @Embedded
    private Location location;

    /**
     * Organization that owns/operates this ground.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @IndexedEmbedded
    private Organization org;

    /**
     * Daily Regular Opening Time.
     */
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime openingTime;

    /**
     * Daily Regular Closing Time.
     */
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime closingTime;

    /**
     * Cost of booking per hour.
     */
    private BigDecimal cost;

    /**
     * Number of separate grounds -defaults to 1.
     */
    private int count = 1;

    /**
     * Status of the ground.
     */
    @JsonIgnore
    private GroundStatus status;

    @OneToMany(mappedBy = "ground", fetch = FetchType.EAGER)
    private List<Image> images;

    @Transient
    private String addressSingleLine;

    @PostLoad
    public void postLoad() {
        if (this.address != null)
            this.addressSingleLine = this.address.toString();
    }
}
