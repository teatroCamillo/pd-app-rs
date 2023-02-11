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
        List<String> answers = new ArrayList<>();

        if(user.isPresent()) {

            answers.add(riskTest.getA1());
            answers.add(riskTest.getA2());
            answers.add(riskTest.getA3());
            answers.add(riskTest.getA4());
            answers.add(riskTest.getA5());
            answers.add(riskTest.getA6());
            answers.add(riskTest.getA7());
            answers.add(riskTest.getA8());
            answers.add(riskTest.getA9());
            answers.add(riskTest.getA10());
            answers.add(riskTest.getA11());
            answers.add(riskTest.getA12());
            answers.add(riskTest.getA13());

            riskTest.setId(UUID.randomUUID().toString());
            riskTest.setRtResult(analyzeAnswers(answers));
            riskTest.setCreatedAt(new Date());
            riskTest.setUser(user.get());

            riskTestRepository.save(riskTest);
            return Optional.of(riskTest);
        }

        return Optional.empty();
    }

    public RiskTest updateRiskTest(String userId, RiskTest riskTest) {

        RiskTest testToUpdate = riskTestRepository.findByUserId(userId);
        List<String> answers = new ArrayList<>();

        if(testToUpdate != null){

            answers.add(riskTest.getA1());
            answers.add(riskTest.getA2());
            answers.add(riskTest.getA3());
            answers.add(riskTest.getA4());
            answers.add(riskTest.getA5());
            answers.add(riskTest.getA6());
            answers.add(riskTest.getA7());
            answers.add(riskTest.getA8());
            answers.add(riskTest.getA9());
            answers.add(riskTest.getA10());
            answers.add(riskTest.getA11());
            answers.add(riskTest.getA12());
            answers.add(riskTest.getA13());

            testToUpdate.setA1(riskTest.getA1());
            testToUpdate.setA2(riskTest.getA2());
            testToUpdate.setA3(riskTest.getA3());
            testToUpdate.setA4(riskTest.getA4());
            testToUpdate.setA5(riskTest.getA5());
            testToUpdate.setA6(riskTest.getA6());
            testToUpdate.setA7(riskTest.getA7());
            testToUpdate.setA8(riskTest.getA8());
            testToUpdate.setA9(riskTest.getA9());
            testToUpdate.setA10(riskTest.getA10());
            testToUpdate.setA11(riskTest.getA11());
            testToUpdate.setA12(riskTest.getA12());
            testToUpdate.setA13(riskTest.getA13());

            testToUpdate.setRtResult(analyzeAnswers(answers));
            riskTestRepository.save(testToUpdate);
        }
        return testToUpdate;
    }


    private String analyzeAnswers(List<String> answers){
        int score = 0;

        for(int i=0; i < answers.size(); i++){
            String e = answers.get(i);
            if(i == 8 || i == 9 && e.startsWith("b")) score+=3;
            else if(e.startsWith("a")) score+=1;
            else if(e.startsWith("b")) score+=2;
            else if(e.startsWith("c")) score+=3;
            else if(e.startsWith("d")) score+=4;
        }

        String result = "";
        if(score <= 18) result = LOW_RISK_TOLERANCE;
        else if(score <= 22) result = BELOW_AVERAGE_RISK_TOLERANCE;
        else if(score <= 28) result = AVERAGE_RISK_TOLERANCE;
        else if(score <= 32) result = ABOVE_AVERAGE_RISK_TOLERANCE;
        else result = HIGH_RISK_TOLERANCE;

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
