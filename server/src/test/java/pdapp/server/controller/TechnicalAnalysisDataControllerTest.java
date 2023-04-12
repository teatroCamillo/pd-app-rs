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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pdapp.server.auth.SpringSecurityConfigurationBasicAuth;
import pdapp.server.service.MacroAnalysisDataService;
import pdapp.server.service.TechnicalAnalysisDataService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MacroAnalysisDataController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringSecurityConfigurationBasicAuth.class })
class TechnicalAnalysisDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TechnicalAnalysisDataService technicalAnalysisDataService;

    @MockBean
    private SpringSecurityConfigurationBasicAuth configurationBasicAuth;

    @MockBean
    private TechnicalAnalysisDataController technicalAnalysisDataController;

    @Test
    void should_return_status_ok_getting_tech_strategy() throws Exception {
        given(technicalAnalysisDataService.strategy(ArgumentMatchers.any()))
                .willAnswer(i -> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cf5202e6-45ec-440e-ae17-b5662ec69f26/tech-analysis"))
                        .andExpect(status().isOk());
    }
}