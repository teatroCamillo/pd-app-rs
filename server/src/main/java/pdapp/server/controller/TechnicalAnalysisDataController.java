package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TechnicalAnalysisDataController {

    private final String  EXCHANGE_RATES_API = "https://currency-conversion-and-exchange-rates.p.rapidapi" +
            ".com/timeseries";


    @GetMapping("/{userId}/tech-analysis")
    public ResponseEntity<?> getAllQuestions(){
        log.info("Response Client");
        String url = EXCHANGE_RATES_API + "/timeseries?start_date=2022-04-01&end_date=2022-05-01";
        WebClient client = WebClient.create();
        Mono<String> resp = client
                .get()
                .uri(url)
                //.accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> {
                    httpHeaders.set("X-RapidAPI-Key", "9d9a08ee00msh6744e3e63ba8dc3p1fe26bjsnec162205585c");
                    httpHeaders.set("X-RapidAPI-Host", "currency-conversion-and-exchange-rates.p.rapidapi.com");
                })
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(String.class);
                    }
                    else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
                //.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
                //.bodyToMono(ClientResponse.class);


        resp.subscribe(log::info);


        return new ResponseEntity<>(resp.subscribe(), HttpStatus.OK);
    }

}
