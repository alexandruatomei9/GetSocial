package info.getsocial.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Component;

import info.getsocial.domain.UserTeam;

@Component
public class ReferenceToUserTeamsTransformer extends Transformer<org.springframework.social.facebook.api.Reference, UserTeam>{

	@Autowired
	Facebook facebook;
	
	@Override
	public UserTeam transform(Reference ref) {
		UserTeam team = new UserTeam();
		team.setId(ref.getId());
		team.setName(ref.getName());
		team.setPhoto("https://graph.facebook.com/v2.5/" + ref.getId() + "/picture");
		return team;
	}

}
