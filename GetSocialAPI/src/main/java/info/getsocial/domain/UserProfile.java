package info.getsocial.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.social.facebook.api.AgeRange;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.WorkEntry;

@Entity
@Table(name = "user_profile")
public class UserProfile {
	
	@Id
	private String id;
	
	@JsonIgnore
	private AgeRange ageRange;

	@JsonIgnore
	private String email;

	@NotNull
	private String firstName;
    
	@NotNull
	private String gender;

	@JsonIgnore
	private Reference hometown;

	@NotNull
	private String lastName;
    
	@JsonIgnore
	private Locale locale;

	@Transient
	private List<WorkEntry> work;

	@ElementCollection
	private List<String> interestedIn;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<UserBook> books;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<UserMovie> movies;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<UserMusic> music;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<UserTeam> teams;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<UserAthlete> athletes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AgeRange getAge_range() {
		return ageRange;
	}

	public void setAge_range(AgeRange age_range) {
		this.ageRange = age_range;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Reference getHometown() {
		return hometown;
	}

	public void setHometown(Reference hometown) {
		this.hometown = hometown;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<WorkEntry> getWork() {
		return work;
	}

	public void setWork(List<WorkEntry> work) {
		this.work = work;
	}

	public List<String> getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(List<String> interestedIn) {
		this.interestedIn = interestedIn;
	}

	public Set<UserBook> getBooks() {
		return books;
	}

	public void setBooks(Set<UserBook> books) {
		this.books = books;
	}

	public Set<UserMovie> getMovies() {
		return movies;
	}

	public void setMovies(Set<UserMovie> movies) {
		this.movies = movies;
	}

	public Set<UserMusic> getMusic() {
		return music;
	}

	public void setMusic(Set<UserMusic> music) {
		this.music = music;
	}

	public Set<UserTeam> getTeams() {
		return teams;
	}

	public void setTeams(Set<UserTeam> teams) {
		this.teams = teams;
	}

	public Set<UserAthlete> getAthletes() {
		return athletes;
	}

	public void setAthletes(Set<UserAthlete> athletes) {
		this.athletes = athletes;
	}

	public void addBooks(List<UserBook> books) {
		if(this.books == null){
			this.books = new HashSet<UserBook>();
		}
		
		this.books.addAll(books);
	}
	
	public void addMovies(List<UserMovie> movies) {
		if(this.movies == null){
			this.movies = new HashSet<UserMovie>();
		}
		
		this.movies.addAll(movies);
	}
	
	public void addMusic(List<UserMusic> music) {
		if(this.music == null){
			this.music = new HashSet<UserMusic>();
		}
		
		this.music.addAll(music);
	}
	
	public void addTeams(List<UserTeam> teams) {
		if(this.teams == null){
			this.teams = new HashSet<UserTeam>();
		}
		
		this.teams.addAll(teams);
	}
	
	public void addAthletes(List<UserAthlete> athletes) {
		if(this.athletes == null){
			this.athletes = new HashSet<UserAthlete>();
		}
		
		this.athletes.addAll(athletes);
	}
}
