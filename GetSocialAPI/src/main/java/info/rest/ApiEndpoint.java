package info.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Madalina on 11/2/2015.
 */

@Component
@Path("/api")
public class ApiEndpoint {

    @GET
    @Path("/hello")
    public Response sayHello() {

        String result = "Hello World";

        return Response.status(200).entity(result).build();

    }
}
