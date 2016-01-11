package info.getsocial.rest;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Component;

@Component
@Path("/user")
public class UserResource {

	@Autowired
	Facebook facebook;
	
	@Autowired
	ResourceInteractor resourceInteractor;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/details")
	public Response retrieveUserDetails() {
		return resourceInteractor.retrieveUserDetails(null);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/books")
	@Transactional
	public Response retrieveUserBooks() {
		return resourceInteractor.retrieveUserBooks(null);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/movies")
	@Transactional
	public Response retrieveUserMovies() {
		return resourceInteractor.retrieveUserMovies(null);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/music")
	@Transactional
	public Response retrieveUserMusic() {
		return resourceInteractor.retrieveUserMusic(null);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/teams")
	@Transactional
	public Response retrieveUserSportTeams() {
		return resourceInteractor.retrieveUserSportTeams(null);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/athletes")
	@Transactional
	public Response retrieveUserSportAthletes() {
		return resourceInteractor.retrieveUserSportAthletes(null);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/friends")
	public Response retrieveUserFriendsIds() {
		PagedList<Reference> friendsList = facebook.friendOperations().getFriends();
		if (friendsList != null && !friendsList.isEmpty()) {
			return Response.ok(friendsList).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
