package info.getsocial.dao.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import info.getsocial.AbstractTest;
import info.getsocial.domain.UserAccount;

public class DaoUserImplTests extends AbstractTest {
	
	@Autowired
	private DaoUserImpl userDao;

	@Before
	public void setup() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void shouldWireUserDao() {
		Assert.assertNotNull(userDao);
	}

	@Test
	public void testThatUserDaoIsProperInitialized() {
	
	}

}
