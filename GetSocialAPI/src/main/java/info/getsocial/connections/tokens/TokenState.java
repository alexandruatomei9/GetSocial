package info.getsocial.connections.tokens;

public abstract class TokenState {
	
	public void checkState( Token token ) {
        token.setState( null );
	}
}
