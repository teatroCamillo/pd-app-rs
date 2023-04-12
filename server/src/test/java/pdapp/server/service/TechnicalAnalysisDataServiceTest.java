package pdapp.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pdapp.server.model.CoreBar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static pdapp.server.util.Constant.*;

@ExtendWith(SpringExtension.class)
@Slf4j
class TechnicalAnalysisDataServiceTest {

    private final ObjectMapper om = new ObjectMapper();
    private final TechnicalAnalysisDataService tads = new TechnicalAnalysisDataService(new OutcomeService());
    private static Map<String, String> techCustomExpectedResult;
    private static Map<String, String> techRealExpectedResult;

    @BeforeAll
    static void init(){
        techCustomExpectedResult = new HashMap<>();
        techCustomExpectedResult.put(CURRENT_PRICE, "1.0019");
        techCustomExpectedResult.put(RSI_14, "60.3070");
        techCustomExpectedResult.put(RSI_14_POINTS, "0");
        techCustomExpectedResult.put(MACD, "0.0038");
        techCustomExpectedResult.put(MACD_POINTS, "0");
        techCustomExpectedResult.put(STRATEGY_MET, "true");
        techCustomExpectedResult.put(STRATEGY_MET_POINTS, "20");
        techCustomExpectedResult.put(TECH_POINTS, "20");

        techRealExpectedResult = new HashMap<>();
        techRealExpectedResult.put(CURRENT_PRICE, "1.0165");
        techRealExpectedResult.put(RSI_14, "65.0073");
        techRealExpectedResult.put(RSI_14_POINTS, "0");
        techRealExpectedResult.put(MACD, "0.0059");
        techRealExpectedResult.put(MACD_POINTS, "0");
        techRealExpectedResult.put(STRATEGY_MET, "false");
        techRealExpectedResult.put(STRATEGY_MET_POINTS, "0");
        techRealExpectedResult.put(TECH_POINTS, "0");
    }

    @Test
    void strategy_returns_correct_result_for_custom_data() {
        List<CoreBar> input = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource("/static/tech/data-eur-usd-29-days-CUSTOM.json");
            File file = resource.getFile();
            input = om.readValue(file, new TypeReference<>(){});
        } catch (Exception e){
            log.info(e.getMessage());
        }

        Map<String, String> resultMap = tads.strategy(input);
        assertEquals(techCustomExpectedResult, resultMap);
    }

    @Test
    void strategy_returns_correct_result_for_real_data() {
        List<CoreBar> input = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource("/static/tech/data-eur-usd-29-days-REAL.json");
            File file = resource.getFile();
            input = om.readValue(file, new TypeReference<>(){});
        } catch (Exception e){
            log.info(e.getMessage());
        }

        Map<String, String> resultMap = tads.strategy(input);
        assertEquals(techRealExpectedResult, resultMap);
    }
}