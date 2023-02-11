package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pdapp.server.service.GamblingTestService;
import pdapp.server.service.OutcomeService;
import pdapp.server.service.RiskTestService;

import java.util.HashMap;
import java.util.Map;

import static pdapp.server.util.Constant.PERSONAL_POINTS;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class PersonalDataController {

    private final GamblingTestService gamblingTestService;

    private final RiskTestService riskTestService;

    private final OutcomeService os;
    @Autowired
    public PersonalDataController(final GamblingTestService gamblingTestService, final RiskTestService riskTestService,
                                  final OutcomeService os) {
        this.gamblingTestService = gamblingTestService;
        this.riskTestService = riskTestService;
        this.os = os;
    }

    @GetMapping("/{userId}/get-personal-result")
    public ResponseEntity<?> getUserGamblingTestResult(@PathVariable String userId){

        String gamblingResult = gamblingTestService.getUserGamblingTestResult(userId);
        String riskResult = riskTestService.getUserRiskTestResult(userId);

        Map<String, String> results = new HashMap<>();
        results.put("gambling", gamblingResult);
        results.put("risk", riskResult);

        Map<String, String> osMap = new HashMap<>(results);
        osMap.put(PERSONAL_POINTS, String.valueOf(os.calculatePersonal(gamblingResult, riskResult)));
        os.setPersonal(osMap);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
