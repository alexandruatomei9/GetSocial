package info.getsocial.connections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import info.getsocial.AbstractTest;
import info.getsocial.connections.tokens.state.ActiveToken;

public class GoodReadsApiConnectionTest extends AbstractTest {

	@Autowired
	private ConnectionsFactory connectionFactory;

	private GoodReadsApiConnection goodReadsApiConnection;

	@Test
	public void testApiConnection() {
		// given
		goodReadsApiConnection = (GoodReadsApiConnection) connectionFactory.getConnection(SocialNetworks.GOOD_READS);

		// when
		goodReadsApiConnection.connect();

		// then
		assertEquals("AhP5QNwA8oMivhX8zYvjQ", goodReadsApiConnection.getToken().getTokenKey());
		assertEquals("soUrSguRcGksNVA1LqEztmt7wavGMNMUgwAfONkFpFs", goodReadsApiConnection.getToken().getTokenSecret());
		assertTrue(goodReadsApiConnection.getToken().getState() instanceof ActiveToken);
	}
}
