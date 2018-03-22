package sorus.gameon.grounds;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.Is.is;

public class CategoryResourceTest {

    @Test
    public void test() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080").path("gameon/api").path("/grounds/categories");

        // First GET - should be empty
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        assertThat(payload.size(), is(0));

        // Add a category
        JsonObject category = Json.createObjectBuilder().add("name", "Futsal").build();
        response = target.request().put(Entity.json(category));
        assertThat(response.getStatus(), is(201));
        // assertThat (response.getLocation())
        System.out.println(response.getLocation());

        // Get after record addition
        response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        payload = response.readEntity(JsonArray.class);
        assertThat(payload.size(), is(1));
    }
}
