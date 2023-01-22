package pdapp.server.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import pdapp.server.model.TechData;
import pdapp.server.service.TechnicalAnalysisDataService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TechnicalAnalysisDataController {

    private final String  EXCHANGE_RATES_API = "https://currency-conversion-and-exchange-rates.p.rapidapi" +
            ".com/";
    private final WebClient webClient;
    private final ObjectMapper om;
    private final TechnicalAnalysisDataService tads;


    public TechnicalAnalysisDataController(WebClient.Builder builder){
        webClient = builder.baseUrl(EXCHANGE_RATES_API).build();
        om = new ObjectMapper();
        tads = new TechnicalAnalysisDataService();
    }

    @GetMapping("/{userId}/tech-analysis")
    public ResponseEntity<?> getAllQuestions() {
        log.info("Response Client");
        String url = EXCHANGE_RATES_API + "/timeseries?start_date=2022-09-01&end_date=2022-10-31";

//        HashMap resp = webClient
//                .get()
//                .uri(url)
//                .headers(httpHeaders -> {
//                    httpHeaders.set("X-RapidAPI-Key", "9d9a08ee00msh6744e3e63ba8dc3p1fe26bjsnec162205585c");
//                    httpHeaders.set("X-RapidAPI-Host", "currency-conversion-and-exchange-rates.p.rapidapi.com");
//                })
//                .retrieve()
//                .bodyToMono(HashMap.class)
//                .block();

        List<TechData> input = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource("/static/data-eur-usd.json");
            File file = resource.getFile();
            input = om.readValue(file, new TypeReference<>(){});
        } catch (Exception e){
            log.info(e.getMessage());
        }
        //input.forEach(e -> log.info(e.toString()));
        return new ResponseEntity<>(tads.strategy(input), HttpStatus.OK);
    }
}
