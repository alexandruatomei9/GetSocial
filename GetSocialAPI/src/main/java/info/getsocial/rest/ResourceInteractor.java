package info.getsocial.rest;

import java.util.ArrayList;
import java.util.List;

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
import info.getsocial.converter.FacebookPageToUserMusicTransformer;
import info.getsocial.converter.ReferenceToUserAthleteTransformer;
import info.getsocial.converter.ReferenceToUserTeamsTransformer;
import info.getsocial.converter.UserToUserProfileTransformer;
import info.getsocial.domain.UserAthlete;
import info.getsocial.domain.UserBook;
import info.getsocial.domain.UserMovie;
import info.getsocial.domain.UserMusic;
import info.getsocial.domain.UserProfile;
import info.getsocial.domain.UserTeam;
import info.getsocial.service.UserProfileService;

@Component
public class ResourceInteractor {

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

	public ResourceInteractor() {
	}

	public Response retrieveUserDetails(String userId) {
		User user = null;
		if (userId == null) {
			user = facebook.userOperations().getUserProfile();
		} else {
			user = facebook.userOperations().getUserProfile(userId);
		}
		if (user != null) {
			UserProfile usrProfile = userProfileTransformer.transform(user);
			userProfileService.saveUserProfile(usrProfile);
			return Response.ok(usrProfile).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserBooks(String userId) {
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		PagedList<Page> facebookBooks = facebook.likeOperations().getBooks(userId);
		UserProfile profile = userProfileService.getUserProfileById(userId);
		;

		List<UserBook> books = facebookPageToBookTransformer.transform(facebookBooks);
		profile.addBooks(books);
		userProfileService.saveUserProfile(profile);
		if (books != null && !books.isEmpty()) {
			return Response.ok(books).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserMovies(String userId) {
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		PagedList<Page> facebookMovies = facebook.likeOperations().getMovies(userId);
		List<UserMovie> movies = facebookPageToMovieTransformer.transform(facebookMovies);
		UserProfile profile = userProfileService.getUserProfileById(userId);
		profile.addMovies(movies);
		userProfileService.saveUserProfile(profile);
		if (movies != null && !movies.isEmpty()) {
			return Response.ok(movies).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserMusic(String userId) {
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		PagedList<Page> facebookMusic = facebook.likeOperations().getMusic(userId);
		List<UserMusic> music = facebookPageToUserMusicTransformer.transform(facebookMusic);
		UserProfile profile = userProfileService.getUserProfileById(userId);
		profile.addMusic(music);
		userProfileService.saveUserProfile(profile);
		if (music != null && !music.isEmpty()) {
			return Response.ok(music).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserSportTeams(String userId) {
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		User user = facebook.userOperations().getUserProfile(userId);
		List<Reference> favoriteTeams = user.getFavoriteTeams();
		if(favoriteTeams == null) {
			return Response.ok(new ArrayList<>()).build();
		}
		List<UserTeam> teams = referenceToUserTeamsTransformer.transform(favoriteTeams);
		UserProfile profile = userProfileService.getUserProfileById(userId);
		profile.addTeams(teams);
		userProfileService.saveUserProfile(profile);
		if (teams != null && !teams.isEmpty()) {
			return Response.ok(teams).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserSportAthletes(String userId) {
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		User user = facebook.userOperations().getUserProfile(userId);
		List<Reference> favoriteAthletes = user.getFavoriteAtheletes();
		if(favoriteAthletes == null) {
			return Response.ok(new ArrayList<>()).build();
		}
		List<UserAthlete> athletes = referenceToUserAthleteTransformer.transform(favoriteAthletes);
		UserProfile profile = userProfileService.getUserProfileById(userId);
		profile.addAthletes(athletes);
		userProfileService.saveUserProfile(profile);
		if (athletes != null && !athletes.isEmpty()) {
			return Response.ok(athletes).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
