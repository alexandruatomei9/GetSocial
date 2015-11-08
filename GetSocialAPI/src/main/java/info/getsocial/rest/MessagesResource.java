package info.getsocial.rest;

import info.getsocial.domain.Message;
import info.getsocial.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
@Component
public class MessagesResource {

    @Autowired
    private MessageService messageService;
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public String hello() {
        return "Hello World";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<Message> message() {
        return messageService.getMessages();
    }
}
