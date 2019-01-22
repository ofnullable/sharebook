package org.slam.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookTest {

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

    @WithMockUser(username = "default", password = "1234")
    @Test
    public void getOnApplyListTest() throws Exception {
        mvc.perform(get("/my-page/on-apply"))
                .andDo(print());
    }

    @WithMockUser(username = "default", password = "1234")
    @Test
    public void getOnLoanListTest() throws Exception {
        mvc.perform(get("/my-page/on-loan"))
                .andDo(print());
    }

    @WithMockUser(username = "default", password = "1234")
    @Test
    public void getOnResvListTest() throws Exception {
        mvc.perform(get("/my-page/on-resv"))
                .andDo(print());
    }

}
