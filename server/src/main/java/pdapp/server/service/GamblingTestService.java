package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdapp.server.model.GamblingTest;
import pdapp.server.model.User;
import pdapp.server.repository.GamblingTestRepository;
import pdapp.server.repository.UserDetailsRepository;

import java.util.*;

import static pdapp.server.util.Constant.*;

@Service
@Slf4j
public class GamblingTestService {

    private GamblingTestRepository gamblingTestRepository;

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public GamblingTestService(GamblingTestRepository gamblingTestRepository, UserDetailsRepository userDetailsRepository) {
        this.gamblingTestRepository = gamblingTestRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public Optional<GamblingTest> saveGamblingTest(String userId, GamblingTest gamblingTest){
        Optional<User> user = userDetailsRepository.findById(userId);
        if(user.isPresent()) {
            gamblingTest.setId(UUID.randomUUID().toString());
            gamblingTest.setGtResult(analyzeAnswers(gamblingTest.getAnswers()));
            gamblingTest.setCreatedAt(new Date());
            gamblingTest.setUser(user.get());
            gamblingTestRepository.save(gamblingTest);
            return Optional.of(gamblingTest);
        }
        return Optional.empty();
    }

    public GamblingTest updateGamblingTest(String userId, GamblingTest gamblingTest) {
        GamblingTest testToUpdate = gamblingTestRepository.findByUserId(userId);
        if(testToUpdate != null){
            testToUpdate.setAnswers(gamblingTest.getAnswers());
            testToUpdate.setGtResult(analyzeAnswers(gamblingTest.getAnswers()));
            gamblingTestRepository.save(testToUpdate);
        }
        return testToUpdate;
    }

    private String analyzeAnswers(String[] answers){
        int score = 0;
        log.info(Arrays.toString(answers));
        for(String e : answers){
            if(e.equals("Yes")) score+=1;
        }
        String result = NOT_ADDICTED;
        if(score >= 7) result = HIGHLY_ADDICTED;
        else if(score >= 4) result = MODERATELY_ADDICTED;
        return result;
    }

    public Boolean hasUserCompletedGamblingTest(String userId){
        GamblingTest gamblingTest = gamblingTestRepository.findByUserId(userId);
        return gamblingTest != null;
    }

    public String getUserGamblingTestResult(String userId){
        GamblingTest gamblingTest = gamblingTestRepository.findByUserId(userId);
        return gamblingTest.getGtResult();
    }

    public Optional<List<GamblingTest>> getAll(){
        return Optional.ofNullable(gamblingTestRepository.findAll());
    }
}
