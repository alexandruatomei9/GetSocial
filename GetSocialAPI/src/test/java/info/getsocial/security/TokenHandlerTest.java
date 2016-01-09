package info.getsocial.security;

import static javax.xml.bind.DatatypeConverter.printBase64Binary;
import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import info.getsocial.domain.UserAccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenHandlerTest {

	private TokenHandler tokenHandler;

	@Before
	public void init() {
		byte[] secret = new byte[70];
		new SecureRandom().nextBytes(secret);
		tokenHandler = new TokenHandler(secret);
	}

	
	@Test
	public void testCreateToken_SeparatorCharInUsername() {
		final UserAccount user = robbert();

		final UserAccount parsedUser = tokenHandler.parseUserFromToken(tokenHandler.createTokenForUser(user));

		assertEquals(user.getUsername(), parsedUser.getUsername());
	}

	@Test
	public void testParseInvalidTokens_NoParseExceptions() throws JsonProcessingException {
		final String unsignedToken = printBase64Binary(new ObjectMapper().writeValueAsBytes(robbert()));

		testForNullResult("");
		testForNullResult(unsignedToken);
		testForNullResult(unsignedToken + ".");
		testForNullResult(unsignedToken + "." + unsignedToken);
	}

	private void testForNullResult(final String token) {
		final UserAccount result = tokenHandler.parseUserFromToken(token);
		assertNull(result);
	}

	private UserAccount robbert() {
		final UserAccount robbert = new UserAccount();
		robbert.setUsername("Robbert");
		robbert.setId(123456L);
		robbert.setExpires(new Date(new Date().getTime() + 10000000).getTime());
		return robbert;
	}
}
