package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdapp.server.model.RiskTest;
import pdapp.server.service.RiskTestService;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RiskTestController {

    @Autowired
    private RiskTestService riskTestService;

    @PostMapping("/{userId}/risk")
    public ResponseEntity<?> saveRiskTestAnswers(@PathVariable String userId, @RequestBody RiskTest riskTest){

        Optional<RiskTest> savedRiskTest = riskTestService.saveRiskTest(userId, riskTest);

        return savedRiskTest.isPresent() ? ResponseEntity.ok("Answers have been saved successfully") :
                (ResponseEntity<?>) ResponseEntity.badRequest();
    }
}
