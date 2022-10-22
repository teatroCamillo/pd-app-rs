package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdapp.server.model.GamblingTest;
import pdapp.server.model.RiskTest;
import pdapp.server.responses.UserInfo;
import pdapp.server.service.GamblingTestService;
import pdapp.server.service.RiskTestService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class GamblingTestController {

    @Autowired
    private GamblingTestService gamblingTestService;

    @PostMapping("/{userId}/gambling")
    public ResponseEntity<?> saveGamblingTestAnswers(@PathVariable String userId, @RequestBody GamblingTest gamblingTest){

        Optional<GamblingTest> savedGamblingTest = gamblingTestService.saveGamblingTest(userId, gamblingTest);

        return savedGamblingTest.isPresent() ? ResponseEntity.ok("Answers have been saved successfully") :
                (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @PutMapping("/{userId}/gambling")
    public ResponseEntity<?> updateGamblingTestAnswers(@PathVariable String userId, @RequestBody GamblingTest gamblingTest){

        GamblingTest savedGamblingTest = gamblingTestService.updateGamblingTest(userId, gamblingTest);

        return savedGamblingTest != null ? ResponseEntity.ok("Answers have been updated successfully") :
                (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @GetMapping("/{userId}/has-completed-gambling-test")
    public ResponseEntity<?> hasUserCompletedGamblingTest(@PathVariable String userId){

        Boolean hasCompleted = gamblingTestService.hasUserCompletedGamblingTest(userId);

        UserInfo userInfo = new UserInfo();
        userInfo.setHasCompletedGamblingTest(hasCompleted);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/all-gambling-tests")
    public ResponseEntity<?> findAll(){
        Optional<?> gamblingTests = gamblingTestService.getAll();
        return new ResponseEntity<>(gamblingTests, HttpStatus.OK);
    }
}
