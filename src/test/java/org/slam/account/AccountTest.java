package org.slam.account;

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
	public void accountUpdateTest() {
		var account = new Account("default", "pass", "", "");
		accountUpdateService.updatePassword(account);
	}
	
}
