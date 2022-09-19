package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdapp.server.model.GamblingTest;
import pdapp.server.model.User;
import pdapp.server.repository.GamblingTestRepository;
import pdapp.server.repository.UserDetailsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class GamblingTestService {

    @Autowired
    private GamblingTestRepository gamblingTestRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    public Optional<GamblingTest> saveGamblingTest(String userId, GamblingTest gamblingTest){
        Optional<User> user = userDetailsRepository.findById(userId);

        if(user.isPresent()) {
            gamblingTest.setId(UUID.randomUUID().toString());
            gamblingTest.setGtResult(analyzeAnswers());
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
            testToUpdate.setGtResult(analyzeAnswers());
            gamblingTestRepository.save(testToUpdate);
        }
        return testToUpdate;
    }

    private String analyzeAnswers(){
        return "temp result";
    }

    public Boolean hasUserCompletedGamblingTest(String userId){
        GamblingTest gamblingTest = gamblingTestRepository.findByUserId(userId);
        return gamblingTest != null;
    }

    public Optional<List<GamblingTest>> getAll(){
        return Optional.ofNullable(gamblingTestRepository.findAll());
    }
}