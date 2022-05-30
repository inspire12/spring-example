package sunghs.springexample.argumentresolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
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
    @Order(1)
    @DisplayName("헤더에 userInfo가 없는 경우")
    void userInfoArgumentInvalidTest() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/test").headers(httpHeaders))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(result -> {
                    UserInfo response = objectMapper.readValue(result.getResponse().getContentAsString(), UserInfo.class);
                    Assertions.assertNull(response.getId());
                    Assertions.assertNull(response.getName());
                })
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    @DisplayName("헤더에 userInfo가 들어온 경우")
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
