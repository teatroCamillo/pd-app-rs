package pdapp.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pdapp.server.util.Constant.*;

@ExtendWith(SpringExtension.class)
@Slf4j
class MacroAnalysisDataServiceTest {

    private final MacroAnalysisDataService mads = new MacroAnalysisDataService(new OutcomeService());
    private final ObjectMapper om = new ObjectMapper();
    private Map<String, Map<String, List<Float>>> gdp = new HashMap<>();
    private Map<String, Map<String, List<Float>>> inf = new HashMap<>();
    private static Map<String, String> macroCustomExpectedResult;
    private static Map<String, String> macroRealExpectedResult;

    @BeforeAll
    static void init(){
        macroCustomExpectedResult = new HashMap<>();
        macroCustomExpectedResult.put(US_GDP_GROWTH_LATEST_Q, "3.6%");
        macroCustomExpectedResult.put(US_INF, "13.1");
        macroCustomExpectedResult.put(EA_GDP_GROWTH_LATEST_Q, "23.6%");
        macroCustomExpectedResult.put(EA_INF, "2.1");
        macroCustomExpectedResult.put(MACRO_POINTS, "31");
        macroCustomExpectedResult.put(INF_POINTS, "11");
        macroCustomExpectedResult.put(GDP_POINTS, "20");

        macroRealExpectedResult = new HashMap<>();
        macroRealExpectedResult.put(US_GDP_GROWTH_LATEST_Q, "2.7%");
        macroRealExpectedResult.put(US_INF, "6.5");
        macroRealExpectedResult.put(EA_GDP_GROWTH_LATEST_Q, "0.1%");
        macroRealExpectedResult.put(EA_INF, "9.2");
        macroRealExpectedResult.put(MACRO_POINTS, "0");
        macroRealExpectedResult.put(INF_POINTS, "0");
        macroRealExpectedResult.put(GDP_POINTS, "0");
    }

    @Test
    void strategy_return_correct_result_for_custom_data() {
        try{
            Resource resourceGDP = new ClassPathResource("/static/macro/GDP-growth-2020-22-Q-CUSTOM.json");
            File fileGDP = resourceGDP.getFile();

            Resource resourceINF = new ClassPathResource("/static/macro/inflation-rate-CUSTOM.json");
            File fileINF = resourceINF.getFile();
            gdp = om.readValue(fileGDP, new TypeReference<>() {});
            inf = om.readValue(fileINF, new TypeReference<>() {});
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        Map<String, String> result = mads.strategy(gdp, inf);
        assertEquals(macroCustomExpectedResult, result);
    }

    @Test
    void strategy_return_correct_result_for_real_data() {
        try{
            Resource resourceGDP = new ClassPathResource("/static/macro/GDP-growth-2020-22-Q-REAL.json");
            File fileGDP = resourceGDP.getFile();

            Resource resourceINF = new ClassPathResource("/static/macro/inflation-rate-REAL.json");
            File fileINF = resourceINF.getFile();
            gdp = om.readValue(fileGDP, new TypeReference<>() {});
            inf = om.readValue(fileINF, new TypeReference<>() {});
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        Map<String, String> result = mads.strategy(gdp, inf);
        assertEquals(macroRealExpectedResult, result);
    }
}