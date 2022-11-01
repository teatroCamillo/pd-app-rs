package pdapp.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import pdapp.server.model.TechData;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TechnicalAnalysisDataController {

    private final String  EXCHANGE_RATES_API = "https://currency-conversion-and-exchange-rates.p.rapidapi" +
            ".com/";

    private final WebClient webClient;

    public TechnicalAnalysisDataController(WebClient.Builder builder){
        webClient = builder.baseUrl(EXCHANGE_RATES_API).build();
    }

    @GetMapping("/{userId}/tech-analysis")
    public ResponseEntity<?> getAllQuestions() throws JsonProcessingException {
        log.info("Response Client");
        String url = EXCHANGE_RATES_API + "/timeseries?start_date=2022-04-01&end_date=2022-05-01";

//        TechData resp = webClient
//                .get()
//                .uri(EXCHANGE_RATES_API)
//                .headers(httpHeaders -> {
//                    httpHeaders.set("X-RapidAPI-Key", "9d9a08ee00msh6744e3e63ba8dc3p1fe26bjsnec162205585c");
//                    httpHeaders.set("X-RapidAPI-Host", "currency-conversion-and-exchange-rates.p.rapidapi.com");
//                })
//                .retrieve()
//                .bodyToMono(TechData.class)
//                .block();
        ObjectMapper om = new ObjectMapper();


        String jsonStr = "";
        Map<String,Object> result = new HashMap();
        try {
            // Use file from static resources
            //Resource resource = new ClassPathResource("/static/response.json");
            Resource resource = new ClassPathResource("/static/response-short.json");
            // Path to resource for jar
            //InputStream input = resource.getInputStream();

            // Path to resource if you want simply get a file
            File file = resource.getFile();

            // here you can map to object TechData
            //TechData resp = om.readValue(file, TechData.class);
            //jsonStr = om.writeValueAsString(resp);

            // or map to HashMap
            result = new ObjectMapper().readValue(file, new TypeReference<HashMap<String, Object>>() {});

        }catch (Exception e){
            log.info(e.getMessage());
        }

        result.forEach((k,v) -> log.info(k + " : " + v));

        // take value from map from rates key and by Gson convert to string and then to new Map
        Gson gson = new Gson();
        String ent = gson.toJson(result.get("rates"), LinkedHashMap.class);

        Map<String, Object> rats = new ObjectMapper().readValue(ent, new TypeReference<HashMap<String, Object>>() {});
        rats.forEach((k,v) -> log.info(k + " : " + v));

        return new ResponseEntity<>(rats, HttpStatus.OK);
    }

}
