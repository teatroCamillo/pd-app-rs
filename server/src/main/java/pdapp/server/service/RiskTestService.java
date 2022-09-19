package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdapp.server.model.RiskTest;
import pdapp.server.model.User;
import pdapp.server.repository.RiskTestRepository;
import pdapp.server.repository.UserDetailsRepository;

import java.util.*;

@Service
@Slf4j
public class RiskTestService {

    @Autowired
    private RiskTestRepository riskTestRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    public Optional<RiskTest> saveRiskTest(String userId, RiskTest riskTest){
        Optional<User> user = userDetailsRepository.findById(userId);

        if(user.isPresent()) {
            riskTest.setId(UUID.randomUUID().toString());
            riskTest.setRtResult(analyzeAnswers());
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
            testToUpdate.setA1(riskTest.getA1());
            testToUpdate.setA2(riskTest.getA2());
            testToUpdate.setA3(riskTest.getA3());
            testToUpdate.setA4(riskTest.getA4());
            testToUpdate.setA5(riskTest.getA5());
            testToUpdate.setA6(riskTest.getA6());
            testToUpdate.setA7(riskTest.getA7());
            testToUpdate.setRtResult(analyzeAnswers());
            riskTestRepository.save(testToUpdate);
        }
        return testToUpdate;
    }

    private String analyzeAnswers(){
        return "temp result";
    }

    public Boolean hasUserCompletedRiskTest(String userId){
        RiskTest riskTest = riskTestRepository.findByUserId(userId);
        return riskTest != null;
    }

    public Optional<List<RiskTest>> getAll(){
        return Optional.ofNullable(riskTestRepository.findAll());
    }
}
