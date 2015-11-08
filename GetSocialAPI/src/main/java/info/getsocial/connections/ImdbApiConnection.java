package info.getsocial.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import info.getsocial.domain.Movie;
import info.getsocial.domain.exception.GetSocialException;

public class ImdbApiConnection implements SocialApiConnection {

	private final static String API_URL = "http://www.omdbapi.com/";

	@Override
	public void connect() {
		//this api do not needs a connection, every query can be submitted individually
	}

	public Movie getMovieByTitle(String title) throws GetSocialException{
		if(StringUtils.isEmpty(title)) {
			throw new GetSocialException(418);
		}
		try {
			String movieUrl = API_URL + "?t=" + URLEncoder.encode(title, "UTF-8") + "&type=movie";
			InputStream input = new URL(movieUrl).openStream();
			Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"),
					new TypeToken<Map<String, String>>(){}.getType());
			
			Movie movie = new Movie();
			movie.setTitle(map.get("Title"));
			movie.setYear(map.get("Year"));
			movie.setReleased(map.get("Released"));
			movie.setRuntime(map.get("Runtime"));
			movie.setGenre(map.get("Genre"));
			movie.setActors(map.get("Actors"));
			movie.setPlot(map.get("Plot"));
			movie.setImdbRating(map.get("imdbRating"));
			movie.setPoster(map.get("Poster"));
			return movie;
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			throw new GetSocialException(500);
		}
	}
}
