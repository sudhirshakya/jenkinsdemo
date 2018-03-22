package sorus.gameon.grounds.service;

import java.io.InputStream;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import sorus.gameon.grounds.dao.GroundDao;
import sorus.gameon.grounds.entities.Ground;
import sorus.gameon.grounds.entities.Image;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;

/**
 * Default Game ground service.
 * 
 * @author Sudhir Shakya &lt;sudhirshakya@yahoo.com&gt;
 *
 */
@Stateless
public class DefaultGroundService implements GroundService {

    @Inject
    private GroundDao dao;

    @Inject
    private Logger logger;

    @Override
    public List<Ground> list(long since) {
        logger.debug("List of all grounds");
        return dao.list(since);
    }

    @Override
    public Ground find(long id) {
        logger.debug("Find single ground using id (%d)", id);
        return dao.findById(id);
    }

    @Override
    public Ground create(Ground ground) {
        logger.debug("Creating new ground %s", ground);

        ground.setLastModified(System.currentTimeMillis());
        return dao.create(ground);
    }

    @Override
    public Image addImage(long id, InputStream is, String filename) {

        Ground ground = find(id);

        int lineNumber = 0;
        for (Image image : ground.getImages())
            if (image.getLineNumber() > lineNumber)
                lineNumber = image.getLineNumber();
        lineNumber++;

        Image image = new Image();
        image.setGround(ground);
        image.setName(filename);
        image.setLineNumber(lineNumber);

        String keyFormat = "ktm/%d/%d.jpg";

        String key = String.format(keyFormat, id, lineNumber);
        System.out.println("Uploading file to " + key);

        // Upload to S3
        AWSCredentials credentials = new BasicAWSCredentials("AKIAIYFIFJMF5H4UM4MQ",
                "HIQKnPtaLvSSXWTdmrTz8K4TxTWXodXkMLGTrkHr");
        AmazonS3Client client = new AmazonS3Client(credentials);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("Content-Type: image/jpeg");
        PutObjectResult result = client.putObject("sorus.gameon", key, is, metadata);
        String md5 = result.getContentMd5();

        System.out.println("MD5: " + result.getContentMd5());

        image.setMd5(md5);
        image.setUrl();

        ground.getImages().add(image);
        dao.update(image);

        return image;

    }

    @Override
    public List<Ground> search(String searchTerms) {
        return dao.search(searchTerms);
    }

}
