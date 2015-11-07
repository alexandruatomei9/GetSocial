package info.getsocial.connections.tokens;

import java.util.Date;

public class Token {
	private TokenState state;
	private Date activeUntil;
	private String tokenKey;
	private String tokenSecret;

	public TokenState getState() {
		return state;
	}

	public void setState(TokenState state) {
		this.state = state;
	}

	public Date getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}
	
	public void checkState() {
		state.checkState(this);
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
}
