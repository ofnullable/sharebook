package org.slam.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void init() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@WithMockUser(username = "username", password = "pass", roles = "ADMIN")
	@Test
	public void accessSuccessTest() throws Exception {
		Assert.isTrue(
				mvc.perform(get("/admin/user/list"))
						.andExpect(status().isOk())
						.andDo(print())
						.andReturn().getResponse().getContentAsString().contains("username")
				, "Can not access /admin/user/list"
		);
	}
	
	@WithMockUser(username = "default", password = "pass", roles = "BASIC")
	@Test
	public void accessDeniedTest() throws Exception {
		mvc.perform(get("/admin/user/list"))
				.andExpect(status().isForbidden())
				.andDo(print());
	}
	
	@Test
	public void signInTest() throws Exception {
		var requestBuilder = formLogin().loginProcessingUrl("/sign-in")
				.userParameter("username").user("username")
				.passwordParam("password").password("pass");
		mvc.perform( requestBuilder )
				.andExpect(redirectedUrl("/"))
				.andDo(print());
	}
	
}
