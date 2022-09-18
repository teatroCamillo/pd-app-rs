package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pdapp.server.model.RiskQuestion;
import pdapp.server.repository.RiskQuestionsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RiskQuestionsController {

    @Autowired
    private RiskQuestionsRepository riskQuestionsRepository;

    @GetMapping("/get-all-risk-questions")
    public ResponseEntity<?> getAllQuestions(){
        Optional<List<RiskQuestion>> result = Optional.of(riskQuestionsRepository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
