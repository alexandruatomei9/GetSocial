package info.getsocial.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserBook {
	private int id;
	private String title;
	private String isbn;
	private double rating;
	private int ratingCount;
	private int numPages;
	private String description;
	private URL imageURL;
	private URL thumbnailURL;
	private String link;
	private List<String> authors;

	public UserBook() {
		authors = new ArrayList<String>();
	}

	public UserBook(int id, String title, int rating, String description, String author) {
		super();
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double d) {
		this.rating = d;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setImageURL(String imageURL) {
		try {
			this.imageURL = new URL(imageURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public URL getImageURL() {
		return imageURL;
	}

	public void setThumbnailURL(String thumbnailURL) {
		try {
			this.thumbnailURL = new URL(thumbnailURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public URL getThumbnailURL() {
		return thumbnailURL;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void addAuthor(String name) {
		authors.add(name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ");
		sb.append(id + "\n\t");
		sb.append(title + "\n\t");
		sb.append(isbn + "\n\t");
		sb.append(rating + "\n\t");
		sb.append(ratingCount + "\n\t");
		sb.append(numPages + "\n\t");
		sb.append(description + "\n\t");
		sb.append(imageURL.toString() + "\n\t");
		sb.append(thumbnailURL.toString() + "\n\t");
		sb.append(link + "\n\t");
		for (String author : authors) {
			sb.append(author);
		}

		return sb.toString();
	}

}
