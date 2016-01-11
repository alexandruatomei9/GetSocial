package info.getsocial.converter;

import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Component;

import info.getsocial.domain.UserAthlete;

@Component
public class ReferenceToUserAthleteTransformer extends Transformer<org.springframework.social.facebook.api.Reference, UserAthlete>{

	@Override
	public UserAthlete transform(Reference ref) {
		UserAthlete athlete = new UserAthlete();
		athlete.setId(ref.getId());
		athlete.setName(ref.getName());
		athlete.setPhoto("https://graph.facebook.com/v2.5/" + ref.getId() + "/picture");
		return athlete;
	}

}
