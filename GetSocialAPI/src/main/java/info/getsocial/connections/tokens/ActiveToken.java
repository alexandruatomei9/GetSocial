package info.getsocial.connections.tokens;

import java.util.Date;

public class ActiveToken extends TokenState{

	public void checkState( Token token ) {
		if(token.getActiveUntil().getTime() < new Date().getTime()) {
			token.setState( new ExpiredToken() );
		} else {
			token.setState( new InvalidToken() );
		}
	}
}
