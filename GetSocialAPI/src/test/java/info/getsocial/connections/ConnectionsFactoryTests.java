package info.getsocial.connections;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import info.getsocial.AbstractTest;

public class ConnectionsFactoryTests extends AbstractTest {

	@Autowired
	private ConnectionsFactory connectionFactory;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testThatConnectionFactoryIsSingleton() {
		Assert.assertSame(ConnectionsFactory.getInstance(), ConnectionsFactory.getInstance());
	}

	@Test
	public void testThatConnectionFactoryReturnsFacebook() {
		SocialApiConnection apiConnection = connectionFactory.getConnection(SocialNetworks.FACEBOOK);
		Assert.assertTrue(apiConnection instanceof FacebookApiConnection);
	}

	@Test
	public void testThatConnectionFactoryReturnsIMDB() {
		SocialApiConnection apiConnection = connectionFactory.getConnection(SocialNetworks.IMDB);
		Assert.assertTrue(apiConnection instanceof ImdbApiConnection);
	}
	@Test
	public void testThatConnectionFactoryReturnsArtist() {
		SocialApiConnection apiConnection = connectionFactory.getConnection(SocialNetworks.ARTIST_LINK);
		Assert.assertTrue(apiConnection instanceof ArtistLinkApiConnection);
	}
	

}
