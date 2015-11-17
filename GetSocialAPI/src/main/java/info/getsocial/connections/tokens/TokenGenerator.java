package info.getsocial.connections.tokens;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import info.getsocial.connections.SocialNetworks;
import info.getsocial.connections.tokens.state.ActiveToken;
import info.getsocial.domain.exception.GetSocialException;

public class TokenGenerator {
	public static final String propFileName = "tokens.properties";

	public Token getToken(SocialNetworks socialNetwork) throws GetSocialException{
		Token token = null;
		Properties props = null;
		try{
			 props = getPropValues();
		} catch(Exception ex){
			throw new GetSocialException(404);
		}
		if(props != null) {
			token = new Token();
			if(socialNetwork.equals(SocialNetworks.GOOD_READS)) {
				token.setState(new ActiveToken());
				token.setTokenKey(props.getProperty(TokenMapping.GOOD_READS_KEY));
				token.setTokenSecret(props.getProperty(TokenMapping.GOOD_READS_SECRET));
			}
		}
		
		return token;
	}
	
	public Properties getPropValues() throws IOException {
		InputStream inputStream = null;
		Properties prop = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}
}
