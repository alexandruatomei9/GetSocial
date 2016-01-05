package info.getsocial.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import info.getsocial.domain.RestResponse;
import info.getsocial.util.Loggable;

@Path("/facebook")
@Component
public class FacebookResource {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    @Loggable
    public RestResponse hello() {
        return new RestResponse(1, "Hello World");
    }
}
