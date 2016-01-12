package info.getsocial.converter;

import org.springframework.social.facebook.api.Page;
import org.springframework.stereotype.Component;

import info.getsocial.domain.UserBook;

@Component
public class FacebookPageToBookTransformer extends Transformer<org.springframework.social.facebook.api.Page, UserBook> {

	@Override
	public UserBook transform(Page page) {
		if (page == null) {
			return null;
		}
		UserBook book = new UserBook();
		book.setId(page.getId());
		book.setName(page.getName());
		String desc;
		if (page.getDescription() != null && page.getDescription().length() > 200) {
			desc = page.getDescription().substring(0, 200) + "...";
		} else {
			desc = page.getDescription();
		}
		book.setDescription(desc);
		book.setCoverUrl(page.getCover() != null ? page.getCover().getSource() : null);
		book.setLikes(page.getLikes());
		return book;
	}

}
