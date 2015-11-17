package info.getsocial.aspect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import info.getsocial.connections.tokens.TokenGenerator;
import info.getsocial.connections.tokens.TokenMapping;
import info.getsocial.domain.exception.GetSocialException;

@Aspect
public class GoodReadsApiConnectionAspect {
    
	TokenGenerator tokenGenerator = new TokenGenerator();
	
	@Before("info.getsocial.connections.GoodReadsApiConnection.connect()")
	public void createDummyPropertiesIfNotExist() throws GetSocialException {
		Properties props = null;
		try{
			 props = tokenGenerator.getPropValues();
		} catch(Exception ex){
			throw new GetSocialException(404);
		}
		
		if(props != null) {
			URL url = getClass().getClassLoader().getResource(TokenGenerator.propFileName);
			if(props.getProperty(TokenMapping.GOOD_READS_KEY) == null){
				props.setProperty(TokenMapping.GOOD_READS_KEY, "Dummy Key");
			}
			
			if(props.getProperty(TokenMapping.GOOD_READS_SECRET) == null){
				props.setProperty(TokenMapping.GOOD_READS_SECRET, "Dummy Secret");
			}
			
			try {
				props.store(new FileOutputStream(new File(url.toURI())), "Added dummy keys for GoodReadsApi");
			} catch (IOException | URISyntaxException e) {
				throw new GetSocialException(404);
			}
		}
	}
}
