package info.getsocial.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_movie")
public class UserMovie extends BaseEntity{
	@Id
	private String id;
	
	@NotNull
	private String title;
	
	@JsonIgnore
    private String year;
	
	@JsonIgnore
    private String released;
	
	@JsonIgnore
    private String runtime;
    
	@JsonIgnore
    private String genre;
    
    @JsonIgnore
    private String actors;
    
    @JsonIgnore
    private String plot;
    
    @JsonIgnore
    private String imdbRating;
    
    @JsonIgnore
    private String poster;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private Set<UserProfile> userProfiles;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}
	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void addUserProfile(UserProfile userProfile) {
		this.userProfiles.add(userProfile);
	}
}
