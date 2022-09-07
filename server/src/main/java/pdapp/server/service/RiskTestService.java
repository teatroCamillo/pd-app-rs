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

        log.info("Risk test UUID");
        log.info(userId);

        Optional<List<RiskTest>> d = hasUserCompletedRiskTest(userId);

        if(d.isPresent()) {
            for (RiskTest e : d.get()) {
                log.info("list");
                log.info(e.getId());
            }
        }
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

    private String analyzeAnswers(){
        return "temp result";
    }

    public Optional<List<RiskTest>> hasUserCompletedRiskTest(String userId){
        return Optional.ofNullable(riskTestRepository.hasUserCompletedRiskTest(userId));
    }
}
