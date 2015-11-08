package info.getsocial.connections;

import org.springframework.stereotype.Component;

@Component
public class ConnectionsFactory {
	private static ConnectionsFactory connectionsFactory;
	
	private ConnectionsFactory() {
	}
	
	public static ConnectionsFactory getInstance() {
		if(connectionsFactory == null) {
			connectionsFactory = new ConnectionsFactory();
		}
		
		return connectionsFactory;
	}
	
	public SocialApiConnection getConnection(SocialNetworks api) {
		if(api.equals(SocialNetworks.ARTIST_LINK)) {
			return new ArtistLinkApiConnection();
		} else if(api.equals(SocialNetworks.FACEBOOK)) {
			return new FacebookApiConnection();
		} else if(api.equals(SocialNetworks.IMDB)) {
			return new ImdbApiConnection();
		}
		throw new IllegalArgumentException("No such api");
	}
}
