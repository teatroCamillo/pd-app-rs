package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pdapp.server.model.Outcome;
import static pdapp.server.util.Consts.*;

import java.util.Map;

@Service
@Slf4j
public class OutcomeService {

    private final Outcome outcome = Outcome.getInstance();

    public void setPersonal(Map<String, String> input){
        outcome.setPersonalMap(input);
    }

    public Map<String, String> getPersonal() {
        return outcome.getPersonalMap();
    }

    public int calculatePersonal(String gamblingResult, String riskResult){
        int result = 0;

        if(gamblingResult.equals(HIGHLY_ADDICTED)) result = 0;
        else if (gamblingResult.equals(MODERATELY_ADDICTED)) result = 3;
        else if (gamblingResult.equals(NOT_ADDICTED)) result = 10;

        if(riskResult.equals(LOW_RISK_TOLERANCE)) result += 10;
        else if(riskResult.equals(BELOW_AVERAGE_RISK_TOLERANCE)) result += 8;
        else if(riskResult.equals(AVERAGE_RISK_TOLERANCE)) result += 4;
        else if(riskResult.equals(ABOVE_AVERAGE_RISK_TOLERANCE)) result += 2;
        else if(riskResult.equals(HIGH_RISK_TOLERANCE)) result += 0;

        return result;
    }
}
