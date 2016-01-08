package info.getsocial.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import info.getsocial.domain.RestResponse;
import info.getsocial.util.Loggable;

@Path("/facebook")
@Component
public class FacebookResource {

	FacebookConnectionFactory connectionFactory =
    	    new FacebookConnectionFactory("921826691257682", "3e6d14f9818f4f5972a9f4d47f67b87a");
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/oauthRedirect")
    @Loggable
    public RestResponse oauthRedirect() {
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:9000/#/oauth_callback");
        //params.add("display", "popup");
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);

        return new RestResponse(1, authorizeUrl);
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/authCode")
    @Loggable
    public RestResponse saveOauthToken(String authorizationCode) {
		JsonElement el = new JsonParser().parse(authorizationCode);
	    JsonObject  jobject = el.getAsJsonObject();
	    String code = jobject.get("authorizationCode").toString();
		
	    OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, "http://localhost:9000/#/oauth_callback", null);
		
		//Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);
		return new RestResponse(1, "http://localhost:9000/#/profile");
    }
}
