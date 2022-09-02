package pdapp.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdapp.server.model.RiskTest;
import pdapp.server.repository.RiskTestRepository;

import java.util.Date;
import java.util.UUID;

@Service
public class RiskTestService {

    @Autowired
    private RiskTestRepository riskTestRepository;


    public RiskTest saveRiskTest(RiskTest riskTest){

        riskTest.setId(UUID.randomUUID());
        riskTest.setRtResult(analyzeAnswers());
        riskTest.setCreatedAt(new Date());

        riskTestRepository.save(riskTest);

        return riskTest;
    }

    private String analyzeAnswers(){
        return "temp result";
    }

}
