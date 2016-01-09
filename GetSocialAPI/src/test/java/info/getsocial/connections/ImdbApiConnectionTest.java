package info.getsocial.connections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import info.getsocial.AbstractTest;
import info.getsocial.domain.UserMovie;
import info.getsocial.domain.exception.GetSocialException;

public class ImdbApiConnectionTest extends AbstractTest{

	@Autowired
	private ConnectionsFactory connectionFactory;
	
	private ImdbApiConnection apiConnection;
	
	@Test
	public void testGetMovie() throws GetSocialException {
		//given		
		String title = "Star Wars";
		apiConnection = (ImdbApiConnection) connectionFactory.getConnection(SocialNetworks.IMDB);
		
		//when
		UserMovie movie = apiConnection.getMovieByTitle(title);
		
		//then
		assertNotNull(movie);
		assertTrue(movie.getTitle().contains(title));
		assertEquals("1977", movie.getYear());
		assertEquals("8.7", movie.getImdbRating());
		assertTrue(movie.getActors().contains("Harrison Ford"));
	}
	
	@Test(expected = GetSocialException.class)
	public void shouldThrowException() throws GetSocialException {
		//given
		apiConnection = (ImdbApiConnection) connectionFactory.getConnection(SocialNetworks.IMDB);
		
		//when
		apiConnection.getMovieByTitle(null);
	}
}
