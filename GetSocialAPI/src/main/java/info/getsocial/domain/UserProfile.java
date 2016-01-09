package info.getsocial.domain;

import java.util.List;
import java.util.Locale;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@JsonIgnore
	private String lastName;
    
	@JsonIgnore
	private Locale locale;

	@Transient
	private List<WorkEntry> work;

	@ElementCollection
	private List<String> interestedIn;

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

}
