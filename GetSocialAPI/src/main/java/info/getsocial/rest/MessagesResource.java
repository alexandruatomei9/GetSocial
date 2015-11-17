package info.getsocial.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import info.getsocial.domain.Message;
import info.getsocial.domain.RestResponse;
import info.getsocial.service.MessageService;
import info.getsocial.util.Loggable;

@Path("/messages")
@Component
public class MessagesResource {

    @Autowired
    private MessageService messageService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    @Loggable
    public RestResponse hello() {
        return new RestResponse(1, "Hello World");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<Message> message() {
        return messageService.getMessages();
    }
}
