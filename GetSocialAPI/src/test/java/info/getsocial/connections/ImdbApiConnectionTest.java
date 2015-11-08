package info.getsocial.connections;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import info.getsocial.domain.Movie;
import info.getsocial.domain.exception.GetSocialException;

public class ImdbApiConnectionTest {

	@Autowired
	private ConnectionsFactory connectionFactory;
	
	private ImdbApiConnection apiConnection;
	
	@Test
	public void testGetMovie() throws GetSocialException {
		//given
		String title = "Star Wars";
		apiConnection = (ImdbApiConnection) connectionFactory.getConnection(SocialNetworks.IMDB);
		
		//when
		Movie movie = apiConnection.getMovieByTitle(title);
		
		//then
		assertNotNull(movie);
	}
	
	@Test(expected = GetSocialException.class)
	public void shouldThrowException() {
		//given
		String title = null;
		apiConnection = (ImdbApiConnection) connectionFactory.getConnection(SocialNetworks.IMDB);
		
		//when
		Movie movie = apiConnection.getMovieByTitle(title);
	}
}
