package info.getsocial.security;

import javax.servlet.Filter;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import info.getsocial.annotations.DefaultTestAnnotations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@DefaultTestAnnotations
public class MockSecurityTest {

	private final String USER_DETAILS_ENDPOINT = "/rest/user/details";
	private final String USER_BOOKS_ENDPOINT = "/rest/user/books";

	private final String AUTH_ENDPOINT = "/auth/facebook";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webAppContext;

	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).addFilters(springSecurityFilterChain).build();
	}

	@Test
	public void test_user_details_unauthorized() throws Exception {
		mockMvc.perform(get(USER_DETAILS_ENDPOINT)).andExpect(status().isUnauthorized())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void test_user_books_unauthorized() throws Exception {
		mockMvc.perform(get(USER_BOOKS_ENDPOINT)).andExpect(status().isUnauthorized())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void test_auth_page_isAccesible() throws Exception {
		mockMvc.perform(get(AUTH_ENDPOINT)).andExpect(status().is3xxRedirection());
	}

}
