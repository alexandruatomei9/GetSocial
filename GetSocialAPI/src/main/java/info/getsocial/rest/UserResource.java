package info.getsocial.rest;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;

import info.getsocial.converter.FacebookPageToBookTransformer;
import info.getsocial.converter.FacebookPageToMovieTransformer;
import info.getsocial.converter.FacebookPageToUserMusicTransformer;
import info.getsocial.converter.ReferenceToUserAthleteTransformer;
import info.getsocial.converter.ReferenceToUserTeamsTransformer;
import info.getsocial.converter.UserToUserProfileTransformer;
import info.getsocial.dao.UserProfileRepository;
import info.getsocial.domain.UserAthlete;
import info.getsocial.domain.UserBook;
import info.getsocial.domain.UserMovie;
import info.getsocial.domain.UserMusic;
import info.getsocial.domain.UserProfile;
import info.getsocial.domain.UserTeam;
import info.getsocial.service.UserProfileService;

@Component
@Path("/user")
public class UserResource {

	@Autowired
	Facebook facebook;
	
	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserToUserProfileTransformer userProfileTransformer;
	
	@Autowired
	FacebookPageToBookTransformer facebookPageToBookTransformer;
	
	@Autowired
	FacebookPageToMovieTransformer facebookPageToMovieTransformer;
	
	@Autowired
	FacebookPageToUserMusicTransformer facebookPageToUserMusicTransformer;
	
	@Autowired
	ReferenceToUserTeamsTransformer referenceToUserTeamsTransformer;
	
	@Autowired
	ReferenceToUserAthleteTransformer referenceToUserAthleteTransformer;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/details")
	public Response retrieveUserDetails() {
		User user = facebook.userOperations().getUserProfile();
		if (user != null) {
			UserProfile usrProfile = userProfileTransformer.transform(user);
			userProfileService.saveUserProfile(usrProfile);
			return Response.ok(usrProfile).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/books")
	@Transactional
	public Response retrieveUserBooks() {
		PagedList<Page> facebookBooks = facebook.likeOperations().getBooks();
		List<UserBook> books = facebookPageToBookTransformer.transform(facebookBooks);
		UserProfile profile = userProfileService.getAuthenticatedUserProfile();
		profile.addBooks(books);
		userProfileService.saveUserProfile(profile);
		if (books != null && !books.isEmpty()) {
			return Response.ok(books).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/movies")
	@Transactional
	public Response retrieveUserMovies() {
		PagedList<Page> facebookMovies = facebook.likeOperations().getMovies();
		List<UserMovie> movies = facebookPageToMovieTransformer.transform(facebookMovies);
		UserProfile profile = userProfileService.getAuthenticatedUserProfile();
		profile.addMovies(movies);
		userProfileService.saveUserProfile(profile);
		if (movies != null && !movies.isEmpty()) {
			return Response.ok(movies).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/music")
	@Transactional
	public Response retrieveUserMusic() {
		PagedList<Page> facebookMusic = facebook.likeOperations().getMusic();
		List<UserMusic> music = facebookPageToUserMusicTransformer.transform(facebookMusic);
		UserProfile profile = userProfileService.getAuthenticatedUserProfile();
		profile.addMusic(music);
		userProfileService.saveUserProfile(profile);
		if (music != null && !music.isEmpty()) {
			return Response.ok(music).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/teams")
	@Transactional
	public Response retrieveUserSportTeams() {
		User user = facebook.userOperations().getUserProfile();
		List<Reference> favoriteTeams = user.getFavoriteTeams();
		List<UserTeam> teams = referenceToUserTeamsTransformer.transform(favoriteTeams);
		UserProfile profile = userProfileService.getAuthenticatedUserProfile();
		profile.addTeams(teams);
		userProfileService.saveUserProfile(profile);
		if (teams != null && !teams.isEmpty()) {
			return Response.ok(teams).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/athletes")
	@Transactional
	public Response retrieveUserSportAthletes() {
		User user = facebook.userOperations().getUserProfile();
		List<Reference> favoriteAthletes = user.getFavoriteAtheletes();
		List<UserAthlete> athletes = referenceToUserAthleteTransformer.transform(favoriteAthletes);
		UserProfile profile = userProfileService.getAuthenticatedUserProfile();
		profile.addAthletes(athletes);
		userProfileService.saveUserProfile(profile);
		if (athletes != null && !athletes.isEmpty()) {
			return Response.ok(athletes).build();
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
