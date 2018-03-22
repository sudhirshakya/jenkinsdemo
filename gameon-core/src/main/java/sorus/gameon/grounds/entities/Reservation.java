package sorus.gameon.grounds.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sorus.gameon.core.AuditedEntity;
import sorus.gameon.core.LocalDateTimeDeserializer;
import sorus.gameon.core.LocalDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQueries({ @NamedQuery(name = Reservation.GROUND, query = "select r from Reservation r where r.ground.id = :groundId and r.startTime >= :start and r.endTime <= :end") })
@Entity
@Table(name = "reservations", indexes = { @Index(columnList = "lastModified") })
public class Reservation extends AuditedEntity {

    private static final String NAMESPACE = "gameon.reservation.";

    public static final String GROUND = NAMESPACE + "ground";

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "reserveId")
    @TableGenerator(name = "reserveId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
    private long id;

    /**
     * Ground reserved.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Ground ground;

    /**
     * Starting Time.
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startTime;

    /**
     * Ending Time.
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endTime;

}
