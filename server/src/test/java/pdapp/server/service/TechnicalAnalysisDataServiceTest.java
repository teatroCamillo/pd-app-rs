package pdapp.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import pdapp.server.model.CoreBar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static pdapp.server.util.Constant.*;

@Slf4j
class TechnicalAnalysisDataServiceTest {

    private ObjectMapper om;
    private TechnicalAnalysisDataService tads;

    @BeforeEach
    void setUp(){
        om = new ObjectMapper();
        tads = new TechnicalAnalysisDataService(new OutcomeService());
    }

    @Test
    void strategyTest() {
        Map<String, String> expected = new HashMap<>();
        expected.put(CURRENT_PRICE, "1.0019");
        expected.put(RSI_14, "60.3070");
        expected.put(RSI_14_POINTS, "0");
        expected.put(MACD, "0.0038");
        expected.put(MACD_POINTS, "0");
        expected.put(STRATEGY_MET, "true");
        expected.put(STRATEGY_MET_POINTS, "20");
        expected.put(TECH_POINTS, "20");

        List<CoreBar> input = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource("/static/tech/data-eur-usd-29-days-CUSTOM.json");
            File file = resource.getFile();
            input = om.readValue(file, new TypeReference<>(){});
        } catch (Exception e){
            log.info(e.getMessage());
        }
        Map<String, String> actual = tads.strategy(input);

        assertEquals(expected, actual);
    }
}