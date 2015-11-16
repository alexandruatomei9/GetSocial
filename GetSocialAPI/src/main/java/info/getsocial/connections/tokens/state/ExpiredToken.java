package info.getsocial.connections.tokens.state;

import java.util.Date;

import info.getsocial.connections.tokens.Token;

public class ExpiredToken extends TokenState {

	public void checkState( Token token ) {
		String newKeyFromApi = "";
		String newKeySecretFromApi = "";
		Date newExpirationDate = new Date();
		token.setTokenKey(newKeyFromApi);
		token.setTokenSecret(newKeySecretFromApi);
        token.setState( new ActiveToken() );
        token.setActiveUntil(newExpirationDate);
	}
}
