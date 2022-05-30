package sunghs.springexample.argumentresolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sunghs.springexample.argumentresolver.model.UserInfo;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserInfoArgumentResolverTest {

    private MockMvc mockMvc;

    private FixtureMonkey fixtureMonkey;

    private final ObjectMapper objectMapper;

    private final WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.fixtureMonkey = FixtureMonkey.create();
    }

    @Test
    void userInfoArgumentValidTest() throws Exception {
        UserInfo userInfo = new UserInfo(100L, "test");

        String userInfoJson = objectMapper.writeValueAsString(userInfo);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("authorization", userInfoJson);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/test").headers(httpHeaders))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(result -> {
                    UserInfo response = objectMapper.readValue(result.getResponse().getContentAsString(), UserInfo.class);
                    Assertions.assertEquals(100, response.getId());
                    Assertions.assertEquals("test", response.getName());
                })
                .andDo(MockMvcResultHandlers.print());
    }
}
