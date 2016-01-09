package info.getsocial.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;

@Component
@Path("/user")
public class UserResource {

	@Autowired
	Facebook facebook;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/details")
	public Response retrieveUserDetails() {
		User user = facebook.userOperations().getUserProfile();
		if (user != null) {
			return Response.ok(user).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/books")
	public Response retrieveUserBooks() {
		PagedList<Page> books = facebook.likeOperations().getBooks();
		if (books != null && !books.isEmpty()) {
			return Response.ok(books).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/friends")
	public Response retrieveUserFriendsIds() {
		PagedList<Reference> friendsLists = facebook.friendOperations().getFriends();
		if (friendsLists != null && !friendsLists.isEmpty()) {

			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
