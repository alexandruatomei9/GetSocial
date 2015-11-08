package info.getsocial.connections;

import info.getsocial.domain.Movie;

public class ImdbApiConnection implements SocialApiConnection {

	private final static String API_URL = "http://www.omdbapi.com/";

	@Override
	public void connect() {
		//this api do not needs a connection, every query can be submitted individually
	}

	public Movie getMovieByTitle(String title){
		return null;
	}
}
