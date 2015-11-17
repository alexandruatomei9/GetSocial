package info.getsocial.connections;

import java.util.Date;

import info.getsocial.connections.tokens.Token;
import info.getsocial.connections.tokens.TokenGenerator;
import info.getsocial.domain.exception.GetSocialException;

public class GoodReadsApiConnection implements SocialApiConnection{

	private TokenGenerator tokenGenerator = new TokenGenerator();
	
	private Token token;
	
	@Override
	public void connect() {
		if(this.getToken() == null || this.getToken().getActiveUntil().before(new Date())){
			try {
				setToken(tokenGenerator.getToken(SocialNetworks.GOOD_READS));
			} catch (GetSocialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Key: " + this.getToken().getTokenKey());
		System.out.println("Secret: " + this.getToken().getTokenSecret());
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

}
