package info.getsocial.converter;

import org.springframework.social.facebook.api.Page;
import org.springframework.stereotype.Component;

import info.getsocial.domain.UserMovie;

@Component
public class FacebookPageToMovieTransformer extends Transformer<org.springframework.social.facebook.api.Page, UserMovie>{

	@Override
	public UserMovie transform(Page page) {
		UserMovie movie = new UserMovie();
		movie.setId(page.getId());
		movie.setGenre(page.getGenre());
		movie.setTitle(page.getName());
		movie.setActors(page.getStarring());
		String plot;
		if(page.getDescription()!= null && page.getDescription().length() > 200){
			plot = page.getDescription().substring(0, 200) + "...";
		} else {
			plot = page.getDescription();
		}
		movie.setPlot(plot);
		movie.setPoster(page.getCover() != null ? page.getCover().getSource() : null);
		movie.setReleased(page.getReleaseDate());
		movie.setRuntime(page.getReleaseDate());
		movie.setYear(page.getReleaseDate());
		return movie;
	}

}
