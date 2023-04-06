package pdapp.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import pdapp.server.auth.JWTTokenHelper;
import pdapp.server.auth.SpringSecurityConfigurationBasicAuth;
import pdapp.server.model.User;
import pdapp.server.service.MacroAnalysisDataService;
import pdapp.server.service.UserService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MacroAnalysisDataController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringSecurityConfigurationBasicAuth.class })
class MacroAnalysisDataControllerTest {

    @TestConfiguration
    static class TestConf{
        @Bean
        public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
            User user = new User();
            user.setMail("test@gmail.com");
            user.setFirstName("TestName");
            user.setLastName("TestLastName");
            user.setEnabled(true);
            user.setPassword(bCryptPasswordEncoder.encode("test"));
            user.setUsername("TestUsername");
            user.setId("cf5202e6-45ec-440e-ae17-b5662ec69f26");


            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(user);
            return manager;
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext applicationContext;

    @MockBean
    private UserService userService;

    @MockBean
    private MacroAnalysisDataService macroAnalysisDataService;

    @MockBean
    private SpringSecurityConfigurationBasicAuth sps;

    @MockBean
    private MacroAnalysisDataController macroAnalysisDataController;

    @MockBean
    private JWTTokenHelper jwtTokenHelper;

    @MockBean
    private AuthenticationEntryPoint authenticationEntryPoint;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //@WithMockUser(value = "TestUsername")
    @Test
    void getMacroResultsTest() throws Exception {

        given(macroAnalysisDataService.strategy(ArgumentMatchers.anyMap(), ArgumentMatchers.anyMap()))
                .willAnswer(i -> i.getArgument(0));

        //then
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/cf5202e6-45ec-440e-ae17-b5662ec69f26/macro-analysis"))
                .andExpect(status().isOk());
    }
}