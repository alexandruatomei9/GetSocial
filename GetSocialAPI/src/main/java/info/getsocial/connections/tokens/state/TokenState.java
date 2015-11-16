package info.getsocial.connections.tokens.state;

import info.getsocial.connections.tokens.Token;

public abstract class TokenState {
	
	public void checkState( Token token ) {
        token.setState( null );
	}
}
