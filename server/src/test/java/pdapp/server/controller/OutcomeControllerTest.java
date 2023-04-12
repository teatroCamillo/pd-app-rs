package pdapp.server.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pdapp.server.auth.SpringSecurityConfigurationBasicAuth;
import pdapp.server.service.OutcomeService;
import pdapp.server.service.TechnicalAnalysisDataService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MacroAnalysisDataController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringSecurityConfigurationBasicAuth.class })
class OutcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OutcomeService outcomeService;

    @MockBean
    private SpringSecurityConfigurationBasicAuth configurationBasicAuth;

    @MockBean
    private OutcomeController outcomeController;

    @Test
    void should_return_status_ok_getting_outcome() throws Exception {
        given(outcomeService.prepareRecommendation(ArgumentMatchers.anyInt()))
                .willAnswer(i -> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cf5202e6-45ec-440e-ae17-b5662ec69f26/get-outcome"))
                .andExpect(status().isOk());
    }
}