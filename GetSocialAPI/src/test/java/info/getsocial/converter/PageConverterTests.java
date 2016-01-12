package info.getsocial.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.CoverPhoto;
import org.springframework.social.facebook.api.Page;

import info.getsocial.AbstractTest;
import info.getsocial.domain.UserBook;
import info.getsocial.domain.UserMovie;

public class PageConverterTests extends AbstractTest {

	private static String PAGE_ID_VALUE = "34329332";
	private static String PAGE_NAME_VALUE = "Page 1";
	private static String PAGE_DESCRIPTION_VALUE = "Description lorem ipsulem";
	private static int PAGE_LIKES_VALUE = 10;
	private static String PAGE_DATE_VALUE = "01-12-2013";
	private static String PAGE_GENRE_VALUE = "Comedy";
	private static String PAGE_ACTORS_VALUE = "George Clooney, Al Pacino";
	private static String PAGE_COVER_SOURCE_VALUE = "https:///";

	private @Autowired FacebookPageToBookTransformer fbToBook;
	private @Autowired FacebookPageToMovieTransformer fbToMovie;

	private @Mock Page page;
	private @Mock CoverPhoto coverPhoto;

	@Before
	public void setupPage() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(coverPhoto.getSource()).thenReturn(PAGE_COVER_SOURCE_VALUE);

		Mockito.when(page.getId()).thenReturn(PAGE_ID_VALUE);
		Mockito.when(page.getName()).thenReturn(PAGE_NAME_VALUE);
		Mockito.when(page.getDescription()).thenReturn(PAGE_DESCRIPTION_VALUE);
		Mockito.when(page.getLikes()).thenReturn(PAGE_LIKES_VALUE);
		Mockito.when(page.getGenre()).thenReturn(PAGE_GENRE_VALUE);
		Mockito.when(page.getStarring()).thenReturn(PAGE_ACTORS_VALUE);
		Mockito.when(page.getReleaseDate()).thenReturn(PAGE_DATE_VALUE);
		Mockito.when(page.getCover()).thenReturn(coverPhoto);

	}

	@Test
	public void testBookTranformerfillsAllValues() {
		UserBook userBook = fbToBook.transform(page);
		Assert.assertEquals("Id must be the same", userBook.getId(), page.getId());
		Assert.assertEquals("Name must be the same", userBook.getName(), page.getName());
		Assert.assertEquals("Description must be the same", userBook.getDescription(), page.getDescription());
		Assert.assertTrue("Likes must be the same", userBook.getLikes() == page.getLikes());
	}

	@Test
	public void testMovieTranformerfillsAllValues() {
		UserMovie userMovie = fbToMovie.transform(page);
		Assert.assertEquals("Id must be the same", userMovie.getId(), page.getId());
		Assert.assertEquals("Name must be the same", userMovie.getTitle(), page.getName());
		Assert.assertEquals("Description must be the same", userMovie.getPlot(), page.getDescription());
		Assert.assertTrue("Likes must be the same", userMovie.getPoster() == page.getCover().getSource());
		Assert.assertEquals("Release Date must be the same", userMovie.getReleased(), page.getReleaseDate());
		Assert.assertEquals("Runtime Date must be the same", userMovie.getRuntime(), page.getReleaseDate());
		Assert.assertEquals("Runtime Date must be the same", userMovie.getYear(), page.getReleaseDate());
		Assert.assertNull(userMovie.getImdbRating());
	}
}
