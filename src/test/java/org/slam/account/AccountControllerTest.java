package org.slam.account;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.account.api.AccountController;
import org.slam.account.domain.Account;
import org.slam.account.exception.AccountNotFoundException;
import org.slam.account.service.AccountFindService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.testutil.AccountUtils.buildNormalAccount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class AccountControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountFindService accountFindService;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .build();
    }

    private Account account = buildNormalAccount();

    @Test
    @DisplayName("존재하는 계정조회")
    public void find_account_by_id() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willReturn(account);

        mvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 계정조회 - 404")
    public void find_account_by_id_failure() throws Exception {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(AccountNotFoundException.class);

        mvc.perform(get("/account/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
