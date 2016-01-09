package info.getsocial.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_book")
public class UserBook {
	
	@Id
	private String id;

	@NotNull
	private String name;
	
	@JsonIgnore
	private String description;
	
	@JsonIgnore
	private Integer likes;
	
	@JsonIgnore
	private String coverUrl;
	
	@JsonIgnore
	private Double rating;
	
	@JsonIgnore
	private Integer ratingCount;
	
	@JsonIgnore
	private String isbn;
	
	@JsonIgnore
	private Integer numPages;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "books")
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String url) {
		this.coverUrl = url;
	}

	public Integer getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNumPages() {
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
}
