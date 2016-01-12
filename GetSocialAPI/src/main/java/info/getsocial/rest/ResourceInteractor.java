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
import info.getsocial.dao.UserAthleteRepository;
import info.getsocial.dao.UserBookRepository;
import info.getsocial.dao.UserMovieRepository;
import info.getsocial.dao.UserMusicRepository;
import info.getsocial.dao.UserTeamRepository;
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
	UserAthleteRepository userAthleteRepo;
	
	@Autowired
	UserBookRepository userBookRepo;
	
	@Autowired
	UserMovieRepository userMovieRepo;
	
	@Autowired
	UserMusicRepository userMusicRepo;
	
	@Autowired
	UserTeamRepository userTeamRepo;

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
		List<UserBook> books = new ArrayList<UserBook>();
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		UserProfile profile = userProfileService.getUserProfileById(userId);
		
		books.addAll(profile.getBooks());
		
		if(books.isEmpty()) {
			PagedList<Page> facebookBooks = facebook.likeOperations().getBooks(userId);
			books = facebookPageToBookTransformer.transform(facebookBooks);
			profile.addBooks(books);
			userProfileService.saveUserProfile(profile);
		}
		
		if (books != null && !books.isEmpty()) {
			return Response.ok(books).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserMovies(String userId) {
		List<UserMovie> movies = new ArrayList<UserMovie>();
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		UserProfile profile = userProfileService.getUserProfileById(userId);
		
		movies.addAll(profile.getMovies());
		
		if(movies.isEmpty()) {
			PagedList<Page> facebookMovies = facebook.likeOperations().getMovies(userId);
			movies = facebookPageToMovieTransformer.transform(facebookMovies);
			profile.addMovies(movies);
			userProfileService.saveUserProfile(profile);
		}
		if (movies != null && !movies.isEmpty()) {
			return Response.ok(movies).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserMusic(String userId) {
		List<UserMusic> music = new ArrayList<UserMusic>();
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		UserProfile profile = userProfileService.getUserProfileById(userId);
		
		music.addAll(profile.getMusic());
		
		if(music.isEmpty()) {
			PagedList<Page> facebookMusic = facebook.likeOperations().getMusic(userId);
			music = facebookPageToUserMusicTransformer.transform(facebookMusic);
			profile.addMusic(music);
			userProfileService.saveUserProfile(profile);
		}
		if (music != null && !music.isEmpty()) {
			return Response.ok(music).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserSportTeams(String userId) {
		List<UserTeam> teams = new ArrayList<UserTeam>();
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		UserProfile profile = userProfileService.getUserProfileById(userId);
		
		teams.addAll(profile.getTeams());
		
		if(teams == null || teams.isEmpty()) {
			User user = facebook.userOperations().getUserProfile(userId);
			List<Reference> favoriteTeams = user.getFavoriteTeams();
			if(favoriteTeams == null) {
				return Response.ok(new ArrayList<>()).build();
			}
			teams = referenceToUserTeamsTransformer.transform(favoriteTeams);
			profile.addTeams(teams);
			userProfileService.saveUserProfile(profile);
		}
		if (teams != null && !teams.isEmpty()) {
			return Response.ok(teams).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public Response retrieveUserSportAthletes(String userId) {
		List<UserAthlete> athletes = new ArrayList<UserAthlete>();
		if (userId == null) {
			userId = userProfileService.getAuthenticatedUserProfileId();
		}
		UserProfile profile = userProfileService.getUserProfileById(userId);
		
		athletes.addAll(profile.getAthletes());
		
		if(athletes == null || athletes.isEmpty()) { 
			User user = facebook.userOperations().getUserProfile(userId);
			List<Reference> favoriteAthletes = user.getFavoriteAtheletes();
			if(favoriteAthletes == null) {
				return Response.ok(new ArrayList<>()).build();
			}
			athletes = referenceToUserAthleteTransformer.transform(favoriteAthletes);
			profile.addAthletes(athletes);
			userProfileService.saveUserProfile(profile);
		}
		if (athletes != null && !athletes.isEmpty()) {
			return Response.ok(athletes).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
