package info.getsocial.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_music")
public class UserMusic extends BaseEntity {
	@Id
	private String id;
	
	@NotNull
	private String name;
    
    @JsonIgnore
    private String genre;
    
    @JsonIgnore
    private String members;
    
    @JsonIgnore
    private String photo;
    
    @JsonIgnore
    private String artistsWeLike;
    
    @JsonIgnore
    private String description;
    
    @JsonIgnore
    private String recordLabel;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "music")
    private Set<UserProfile> userProfiles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMembers() {
		return members;
	}

	public void setMemebers(String members) {
		this.members = members;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getArtistsWeLike() {
		return artistsWeLike;
	}

	public void setArtistsWeLike(String artistsWeLike) {
		this.artistsWeLike = artistsWeLike;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecordLabel() {
		return recordLabel;
	}

	public void setRecordLabel(String recordLabel) {
		this.recordLabel = recordLabel;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
	public void addUserProfile(UserProfile userProfile) {
		this.userProfiles.add(userProfile);
	}
}
