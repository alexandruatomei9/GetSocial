package info.getsocial.converter;

import org.springframework.social.facebook.api.Page;
import org.springframework.stereotype.Component;

import info.getsocial.domain.UserMusic;

@Component
public class FacebookPageToUserMusicTransformer
		extends Transformer<org.springframework.social.facebook.api.Page, UserMusic> {

	@Override
	public UserMusic transform(Page page) {
		if (page == null) {
			return null;
		}
		UserMusic music = new UserMusic();
		music.setId(page.getId());
		music.setArtistsWeLike(page.getArtistsWeLike());
		music.setDescription(page.getBio());
		music.setGenre(page.getInfluences());
		music.setMemebers(page.getBandMembers());
		music.setName(page.getName());
		music.setRecordLabel(page.getRecordLabel());

		return music;
	}

}
