package sorus.gameon.common.entities;

import javax.persistence.Embeddable;

import lombok.Data;

/**
 * Physical address (Nepali) used in business organizations and people.
 *
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Data
@Embeddable
public class Address {

    /**
     * Street Address, e.g. 123 Leknath Marg
     */
    private String streetAddress;

    /**
     * Local neighborhood, e.g. Kumaripati.
     */
    private String neighborhood;

    /**
     * District of the location, e.g. Lalitpur.
     */
    private String district;

    /**
     * Zipcode of the location, e.g. 44011.
     */
    private String zipcode;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (streetAddress != null && !streetAddress.equals("")) {
            sb.append(streetAddress);
            sb.append(", ");
        }
        if (neighborhood != null && !neighborhood.equals("")) {
            sb.append(neighborhood);
            sb.append(", ");
        }
        if (district != null && !district.equals("")) {
            sb.append(district);
        }
        return sb.toString();
    }

}
