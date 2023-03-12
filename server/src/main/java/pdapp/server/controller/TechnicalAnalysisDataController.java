package pdapp.server.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pdapp.server.model.CoreBar;
import pdapp.server.service.OutcomeService;
import pdapp.server.service.TechnicalAnalysisDataService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TechnicalAnalysisDataController {

    private final TechnicalAnalysisDataService tads;
    private final ObjectMapper om;
    private final OutcomeService os;

    @Autowired
    public TechnicalAnalysisDataController(final TechnicalAnalysisDataService tads,
                                           final ObjectMapper om,
                                           final OutcomeService os){
        this.tads = tads;
        this.om = om;
        this.os = os;
    }

    @GetMapping("/{userId}/tech-analysis")
    public ResponseEntity<?> getTechResults() {
        List<CoreBar> input = new ArrayList<>();
        try {
            //Resource resource = new ClassPathResource("/static/tech/data-eur-usd-29-days-REAL.json");
            Resource resource = new ClassPathResource("/static/tech/data-eur-usd-29-days-CUSTOM.json");
            File file = resource.getFile();
            input = om.readValue(file, new TypeReference<>(){});
        } catch (Exception e){
            log.info(e.getMessage());
        }
        Map<String, String> strategyResults = tads.strategy(input);
        os.setTech(strategyResults);
        return new ResponseEntity<>(strategyResults, HttpStatus.OK);
    }
}
