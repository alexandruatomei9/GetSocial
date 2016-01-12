package info.getsocial.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Reference;

import info.getsocial.AbstractTest;
import info.getsocial.domain.UserAthlete;
import info.getsocial.domain.UserTeam;

public class ReferenceConverterTests extends AbstractTest {
	private static String REF_ID_VALUE = "34329332";
	private static String REF_NAME_VALUE = "Page 1";

	private @Autowired ReferenceToUserAthleteTransformer refToAthlete;
	private @Autowired ReferenceToUserTeamsTransformer refToTeam;
	private @Mock Reference reference;

	@Before
	public void setupReference() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(reference.getId()).thenReturn(REF_ID_VALUE);
		Mockito.when(reference.getName()).thenReturn(REF_NAME_VALUE);
	}

	public void testThatReferenceToAthleteReturnsNull() {
		Reference ref = null;
		UserAthlete userAthlete = refToAthlete.transform(ref);
		Assert.assertNull(userAthlete);
	}
	
	@Test
	public void testThatReferenceToAthleteFillsAllValues() {
		UserAthlete userAthlete = refToAthlete.transform(reference);
		Assert.assertEquals("ID must be the same", reference.getId(), userAthlete.getId());
		Assert.assertEquals("Name must be the same", reference.getName(), userAthlete.getName());
		Assert.assertNotNull(userAthlete.getPhoto());
	}

	public void testThatReferenceToMovieReturnsNull() {
		Reference ref = null;
		UserTeam userTeam = refToTeam.transform(ref);
		Assert.assertNull(userTeam);
	}
	
	@Test
	public void testThatReferenceToTeamFillsAllValues() {
		UserTeam userTeam = refToTeam.transform(reference);
		Assert.assertEquals("ID must be the same", reference.getId(), userTeam.getId());
		Assert.assertEquals("Name must be the same", reference.getName(), userTeam.getName());
		Assert.assertNotNull(userTeam.getPhoto());
	}
}
