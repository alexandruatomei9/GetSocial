package info.getsocial.rest;

import java.util.List;

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

import info.getsocial.converter.FacebookPageToBookTransformer;
import info.getsocial.converter.FacebookPageToMovieTransformer;
import info.getsocial.converter.UserToUserProfileTransformer;
import info.getsocial.domain.UserBook;
import info.getsocial.domain.UserMovie;
import info.getsocial.domain.UserProfile;

@Component
@Path("/user")
public class UserResource {

	@Autowired
	Facebook facebook;

	@Autowired
	UserToUserProfileTransformer userProfileTransformer;
	
	@Autowired
	FacebookPageToBookTransformer facebookPageToBookTransformer;
	
	@Autowired
	FacebookPageToMovieTransformer facebookPageToMovieTransformer;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/details")
	public Response retrieveUserDetails() {
		User user = facebook.userOperations().getUserProfile();
		if (user != null) {
			UserProfile usrProfile = userProfileTransformer.transform(user);
			return Response.ok(usrProfile).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/books")
	public Response retrieveUserBooks() {
		PagedList<Page> facebookBooks = facebook.likeOperations().getBooks();
		List<UserBook> books = facebookPageToBookTransformer.transform(facebookBooks);
		if (books != null && !books.isEmpty()) {
			return Response.ok(books).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/movies")
	public Response retrieveUserMovies() {
		PagedList<Page> facebookMovies = facebook.likeOperations().getMovies();
		List<UserMovie> movies = facebookPageToMovieTransformer.transform(facebookMovies);
		if (movies != null && !movies.isEmpty()) {
			return Response.ok(movies).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
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
