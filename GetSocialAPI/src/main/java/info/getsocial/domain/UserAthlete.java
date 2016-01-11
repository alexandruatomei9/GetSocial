package info.getsocial.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_athlete")
public class UserAthlete extends BaseEntity {
	@Id
	private String id;
	
	@NotNull
	private String name;

    private String photo;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "athletes")
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
