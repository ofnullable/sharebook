package org.slam.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slam.dto.account.Account;
import org.slam.service.account.AccountUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountTest {
	
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
	
	@Autowired
	private AccountUpdateService accountUpdateService;

	@Test
	public void signUpTest() throws Exception {
		mvc.perform(
				post("/sign-up")
						.param("username", "test")
						.param("password", "1234")
						.param("name", "test_user")
						.param("email", "test@localhost.com"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/sign-in"))
				.andDo(print());
	}

	@Test
	public void accountUpdateTest() {
		var account = new Account("default", "123", "", "");
		accountUpdateService.updatePassword(account);
	}
	
}
