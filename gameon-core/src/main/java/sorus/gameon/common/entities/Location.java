package sorus.gameon.common.entities;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.Data;

/**
 * GPS longitude/latitude of any location.
 * 
 * The location will be used to plot the point in a map and to generate
 * directions to end-users.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Embeddable
@Data
public class Location {

    /**
     * Longitude of the location.
     */
    private BigDecimal longitude;

    /**
     * Latitude of the location.
     */
    private BigDecimal latitude;
}
