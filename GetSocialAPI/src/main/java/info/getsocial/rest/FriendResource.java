package info.getsocial.rest;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/user/friend/{friendId}")
public class FriendResource {

	@Autowired
	ResourceInteractor resourceInteractor;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/details")
	public Response retrieveUserDetails(@PathParam("friendId") String friendId) {
		return resourceInteractor.retrieveUserDetails(friendId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/books")
	@Transactional
	public Response retrieveUserBooks(@PathParam("friendId") String friendId) {
		return resourceInteractor.retrieveUserBooks(friendId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/movies")
	@Transactional
	public Response retrieveUserMovies(@PathParam("friendId") String friendId) {
		return resourceInteractor.retrieveUserMovies(friendId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/music")
	@Transactional
	public Response retrieveUserMusic(@PathParam("friendId") String friendId) {
		return resourceInteractor.retrieveUserMusic(friendId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/teams")
	@Transactional
	public Response retrieveUserSportTeams(@PathParam("friendId") String friendId) {
		return resourceInteractor.retrieveUserSportTeams(friendId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/athletes")
	@Transactional
	public Response retrieveUserSportAthletes(@PathParam("friendId") String friendId) {
		return resourceInteractor.retrieveUserSportAthletes(friendId);
	}
	
}
