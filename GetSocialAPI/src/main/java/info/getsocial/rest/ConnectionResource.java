package info.getsocial.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import info.getsocial.connections.ConnectionsFactory;
import info.getsocial.connections.GoodReadsApiConnection;
import info.getsocial.connections.SocialNetworks;
import info.getsocial.util.Loggable;

@Path("/connect")
@Component
public class ConnectionResource {

	@Autowired
	private ConnectionsFactory connectionFactory;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable
	public String hello() {
		GoodReadsApiConnection apiConnection = (GoodReadsApiConnection) connectionFactory
				.getConnection(SocialNetworks.GOOD_READS);

		apiConnection.connect();
		
		return "Hello World";
	}
}
