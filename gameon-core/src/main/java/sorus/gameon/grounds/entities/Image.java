package sorus.gameon.grounds.entities;

import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author sudhir
 *
 */
@Entity
@Data
@Table(name = "ground_images")
public class Image {

    private static final String URL_FORMAT = "https://s3.amazonaws.com/sorus.gameon/ktm/%d/%d.jpg";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "imageId")
    @TableGenerator(name = "imageId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
    @JsonIgnore
    private long id;

    private int lineNumber;

    private String name;

    @ManyToOne
    @JsonIgnore
    private Ground ground;

    @Transient
    private URI url;

    @JsonIgnore
    private String md5;

    @PostLoad
    public void setUrl() {
        try {
            this.url = new URI(String.format(URL_FORMAT, ground.getId(), lineNumber));
        } catch (URISyntaxException e) {
            // TODO - Fix error handling
            e.printStackTrace();
        }
    }
}
