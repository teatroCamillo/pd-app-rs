package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdapp.server.model.RiskTest;
import pdapp.server.responses.UserInfo;
import pdapp.server.service.RiskTestService;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{userId}/risk")
    public ResponseEntity<?> updateRiskTestAnswers(@PathVariable String userId, @RequestBody RiskTest riskTest){

        RiskTest savedRiskTest = riskTestService.updateRiskTest(userId, riskTest);

        return savedRiskTest != null ? ResponseEntity.ok("Answers have been updated successfully") :
                (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @GetMapping("/{userId}/has-completed-risk-test")
    public ResponseEntity<?> hasUserCompletedRiskTest(@PathVariable String userId){

        Boolean hasCompleted = riskTestService.hasUserCompletedRiskTest(userId);

        UserInfo userInfo = new UserInfo();
        userInfo.setHasCompletedRiskTest(hasCompleted);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        Optional<?> riskTests = riskTestService.getAll();
        return new ResponseEntity<>(riskTests, HttpStatus.OK);
    }
}
