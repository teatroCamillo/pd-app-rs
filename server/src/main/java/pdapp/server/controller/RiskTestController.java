package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdapp.server.model.RiskTest;
import pdapp.server.service.RiskTestService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/risk")
@Slf4j
public class RiskTestController {

    @Autowired
    private RiskTestService riskTestService;

    @PostMapping
    public ResponseEntity<?> saveRiskTestAnswers(@RequestBody RiskTest riskTest){
        RiskTest savedRiskTest = riskTestService.saveRiskTest(riskTest);

        return savedRiskTest != null ? ResponseEntity.ok("Answers have been saved successfully") :
                (ResponseEntity<?>) ResponseEntity.badRequest();
    }
}
