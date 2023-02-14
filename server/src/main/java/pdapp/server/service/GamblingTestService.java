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
        List<String> answers = new ArrayList<>();

        if(user.isPresent()) {

            answers.add(gamblingTest.getA1());
            answers.add(gamblingTest.getA2());
            answers.add(gamblingTest.getA3());
            answers.add(gamblingTest.getA4());
            answers.add(gamblingTest.getA5());
            answers.add(gamblingTest.getA6());
            answers.add(gamblingTest.getA7());
            answers.add(gamblingTest.getA8());
            answers.add(gamblingTest.getA9());
            answers.add(gamblingTest.getA10());
            answers.add(gamblingTest.getA11());
            answers.add(gamblingTest.getA12());
            answers.add(gamblingTest.getA13());
            answers.add(gamblingTest.getA14());
            answers.add(gamblingTest.getA15());
            answers.add(gamblingTest.getA16());
            answers.add(gamblingTest.getA17());
            answers.add(gamblingTest.getA18());
            answers.add(gamblingTest.getA19());
            answers.add(gamblingTest.getA20());

            gamblingTest.setId(UUID.randomUUID().toString());
            gamblingTest.setGtResult(analyzeAnswers(answers));
            gamblingTest.setCreatedAt(new Date());
            gamblingTest.setUser(user.get());

            gamblingTestRepository.save(gamblingTest);
            return Optional.of(gamblingTest);
        }

        return Optional.empty();
    }

    public GamblingTest updateGamblingTest(String userId, GamblingTest gamblingTest) {

        GamblingTest testToUpdate = gamblingTestRepository.findByUserId(userId);
        List<String> answers = new ArrayList<>();

        if(testToUpdate != null){

            answers.add(gamblingTest.getA1());
            answers.add(gamblingTest.getA2());
            answers.add(gamblingTest.getA3());
            answers.add(gamblingTest.getA4());
            answers.add(gamblingTest.getA5());
            answers.add(gamblingTest.getA6());
            answers.add(gamblingTest.getA7());
            answers.add(gamblingTest.getA8());
            answers.add(gamblingTest.getA9());
            answers.add(gamblingTest.getA10());
            answers.add(gamblingTest.getA11());
            answers.add(gamblingTest.getA12());
            answers.add(gamblingTest.getA13());
            answers.add(gamblingTest.getA14());
            answers.add(gamblingTest.getA15());
            answers.add(gamblingTest.getA16());
            answers.add(gamblingTest.getA17());
            answers.add(gamblingTest.getA18());
            answers.add(gamblingTest.getA19());
            answers.add(gamblingTest.getA20());


            testToUpdate.setA1(gamblingTest.getA1());
            testToUpdate.setA2(gamblingTest.getA2());
            testToUpdate.setA3(gamblingTest.getA3());
            testToUpdate.setA4(gamblingTest.getA4());
            testToUpdate.setA5(gamblingTest.getA5());
            testToUpdate.setA6(gamblingTest.getA6());
            testToUpdate.setA7(gamblingTest.getA7());
            testToUpdate.setA8(gamblingTest.getA8());
            testToUpdate.setA9(gamblingTest.getA9());
            testToUpdate.setA10(gamblingTest.getA10());
            testToUpdate.setA11(gamblingTest.getA11());
            testToUpdate.setA12(gamblingTest.getA12());
            testToUpdate.setA13(gamblingTest.getA13());
            testToUpdate.setA14(gamblingTest.getA14());
            testToUpdate.setA15(gamblingTest.getA15());
            testToUpdate.setA16(gamblingTest.getA16());
            testToUpdate.setA17(gamblingTest.getA17());
            testToUpdate.setA18(gamblingTest.getA18());
            testToUpdate.setA19(gamblingTest.getA19());
            testToUpdate.setA20(gamblingTest.getA20());

            testToUpdate.setGtResult(analyzeAnswers(answers));
            gamblingTestRepository.save(testToUpdate);
        }
        return testToUpdate;
    }

    private String analyzeAnswers(List<String> answers){
        int score = 0;

        for(String e : answers){
            if(e.equals("Yes")) score+=1;
        }

        String result = "";

        if(score >= 7) result = HIGHLY_ADDICTED;
        else if(score >= 4) result = MODERATELY_ADDICTED;
        else result = NOT_ADDICTED;

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
