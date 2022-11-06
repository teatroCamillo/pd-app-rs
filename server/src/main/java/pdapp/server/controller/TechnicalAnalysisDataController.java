package pdapp.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.OverIndicatorRule;
import pdapp.server.service.TechnicalAnalysisDataService;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


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
    public ResponseEntity<?> getAllQuestions() throws JsonProcessingException {
        Map<String, String> resutlMap = new HashMap<>();
        log.info("Response Client");
        String url = EXCHANGE_RATES_API + "/timeseries?start_date=2022-09-01&end_date=2022-10-31";

        // this "resp" can be return as body for this ResponeEntity as json
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


        Map<String,Object> input = new HashMap<>();
        try {
            Resource resource = new ClassPathResource("/static/response.json");
            File file = resource.getFile();
            input = om.readValue(file, new TypeReference<HashMap<String, Object>>() {});

        }catch (Exception e){
            log.info(e.getMessage());
        }

        //input.forEach((k,v) -> log.info(k + " : " + v));

        // prepare data *******************************************************************************************

        // treeMap is auto-sorting data by key
        TreeMap<LocalDate, String> tm = tads.getSortedRatesMapForEURUSD(input);

        //tm.forEach((k,v) -> log.info(k + " : " + v));

        BarSeries series = tads.createBarSeriesFromRatesMap(tm);
        int quantity = series.getBarCount();
        for(int i=0; i<quantity; i++){
            log.info(series.getBar(i).toString());
        }
        // *******************************************************************************************

        // RSI *******************************************************************************************
        int barCount = series.getBarCount();
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        RSIIndicator rsi = new RSIIndicator(closePrice, 14);

        for(int i = 0; i < rsi.getBarSeries().getBarCount(); i++){
            log.info("RSI: " + " index: " + i + " : "  + rsi.getValue(i) + " : " + rsi.getBarSeries().getBar(i));
        }
        resutlMap.put("actualRSI", String.valueOf(rsi.getValue(rsi.getBarSeries().getEndIndex())));
        // *******************************************************************************************

        // 2-period cross RSI ************************************************************************
        // *******************************************************************************************

        return new ResponseEntity<>(resutlMap, HttpStatus.OK);
    }

}
