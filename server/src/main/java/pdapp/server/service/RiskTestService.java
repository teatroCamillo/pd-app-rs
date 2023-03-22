package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdapp.server.model.RiskTest;
import pdapp.server.model.User;
import pdapp.server.repository.RiskTestRepository;
import pdapp.server.repository.UserDetailsRepository;

import java.util.*;
import static pdapp.server.util.Constant.*;

@Service
@Slf4j
public class RiskTestService {

    private final RiskTestRepository riskTestRepository;

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public RiskTestService(RiskTestRepository riskTestRepository, UserDetailsRepository userDetailsRepository) {
        this.riskTestRepository = riskTestRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public Optional<RiskTest> saveRiskTest(String userId, RiskTest riskTest){
        Optional<User> user = userDetailsRepository.findById(userId);
        if(user.isPresent()) {
            riskTest.setId(UUID.randomUUID().toString());
            riskTest.setRtResult(analyzeAnswers(riskTest.getAnswers()));
            riskTest.setCreatedAt(new Date());
            riskTest.setUser(user.get());
            riskTestRepository.save(riskTest);
            return Optional.of(riskTest);
        }
        return Optional.empty();
    }

    public RiskTest updateRiskTest(String userId, RiskTest riskTest) {
        RiskTest testToUpdate = riskTestRepository.findByUserId(userId);
        if(testToUpdate != null){
            testToUpdate.setAnswers(riskTest.getAnswers());
            testToUpdate.setRtResult(analyzeAnswers(riskTest.getAnswers()));
            riskTestRepository.save(testToUpdate);
        }
        return testToUpdate;
    }

    private String analyzeAnswers(String[] answers){
        int score = 0;
        for(int i=0; i < answers.length; i++){
            String e = answers[i];
            if(i == 8 || i == 9 && e.startsWith("b")) score+=3;
            else if(e.startsWith("a")) score+=1;
            else if(e.startsWith("b")) score+=2;
            else if(e.startsWith("c")) score+=3;
            else if(e.startsWith("d")) score+=4;
        }

        String result = HIGH_RISK_TOLERANCE;
        if(score <= 18) result = LOW_RISK_TOLERANCE;
        else if(score <= 22) result = BELOW_AVERAGE_RISK_TOLERANCE;
        else if(score <= 28) result = AVERAGE_RISK_TOLERANCE;
        else if(score <= 32) result = ABOVE_AVERAGE_RISK_TOLERANCE;

        return result;
    }

    public Boolean hasUserCompletedRiskTest(String userId){
        RiskTest riskTest = riskTestRepository.findByUserId(userId);
        return riskTest != null;
    }

    public String getUserRiskTestResult(String userId){
        RiskTest riskTest = riskTestRepository.findByUserId(userId);
        return riskTest.getRtResult();
    }

    public Optional<List<RiskTest>> getAll(){
        return Optional.ofNullable(riskTestRepository.findAll());
    }
}
