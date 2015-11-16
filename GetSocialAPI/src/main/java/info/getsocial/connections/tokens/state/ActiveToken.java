package info.getsocial.connections.tokens.state;

import java.util.Date;

import info.getsocial.connections.tokens.Token;

public class ActiveToken extends TokenState{

	public void checkState( Token token ) {
		if(token.getActiveUntil().getTime() < new Date().getTime()) {
			token.setState( new ExpiredToken() );
		} else {
			token.setState( new InvalidToken() );
		}
	}
}
