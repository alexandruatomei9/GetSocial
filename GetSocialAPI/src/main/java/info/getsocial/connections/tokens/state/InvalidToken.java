package info.getsocial.connections.tokens.state;

import info.getsocial.connections.tokens.Token;

public class InvalidToken extends TokenState{

	public void checkState( Token token ) {
        token.setState( new ExpiredToken() );
        token.setActiveUntil(null);
        token.setTokenKey(null);
        token.setTokenSecret(null);
	}
}
