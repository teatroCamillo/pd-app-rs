package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pdapp.server.service.OutcomeService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class OutcomeController {

    private final OutcomeService os;

    @Autowired
    public OutcomeController(OutcomeService os){
        this.os = os;
    }

    @GetMapping("/{userId}/get-outcome")
    public ResponseEntity<?> getOutcome(){

        Map<String, String> results = new HashMap<>(os.getPersonal());

        results.put("hora", LocalDateTime.now().toString());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
