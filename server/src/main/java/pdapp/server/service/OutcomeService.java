package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pdapp.server.model.Outcome;
import static pdapp.server.util.Constant.*;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OutcomeService {

    private final Outcome outcome = Outcome.getInstance();

    public void setPersonal(Map<String, String> input){
        outcome.setPersonalMap(input);
    }
    public void setTech(Map<String, String> input){
        outcome.setTechMap(input);
    }
    public void setMacro(Map<String, String> input){
        outcome.setMacroMap(input);
    }
//    public void setRecommendation(Map<String, String> input){
//        outcome.setRecommendationMap(input);
//    }

    public Map<String, String> getPersonal() {
        return outcome.getPersonalMap();
    }
    public Map<String, String> getTech() {
        return outcome.getTechMap();
    }
    public Map<String, String> getMacro() {
        return outcome.getMacroMap();
    }
//    public Map<String, String> getRecommendation() {
//        return outcome.getRecommendationMap();
//    }

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

    /**
     * Score the GDP growth.
     *
     * @param qGdpEA
     * @param qGdpUS
     * @return points
     */
    public int compareMacroGdpGrowth(float qGdpEA, float qGdpUS){
        int points = 0;
        int diff = Math.round(qGdpEA - qGdpUS);
        if(diff > 0) points = Math.min(Math.abs(diff), 20);
        return points;
    }

    /**
     * Compare inflation rate for two currencies and set appropriate points.
     *
     * If difference for base currency & quoted currency is negative that means the
     * base currency is strengthening. According to that the points variable is higher.
     *
     * @param eaInf
     * @param usInf
     *
     * @return points
     */
    public int compareMacroInflation(float eaInf, float usInf){
        int points = 0;
        int diff = Math.round(eaInf - usInf);
        if(diff < 0) points = Math.min(Math.abs(diff), 20);
        return points;
    }

    /**
     * Return rsi14 points. The lower the rsi, the higher the score.
     * Max 20.
     *
     * @param rsiResult
     * @return
     */
    public int scoreRSI14(String rsiResult) {
        int rsi = (int)Float.parseFloat(rsiResult);
        int points = rsi > 30 ? 0 : Math.abs(rsi - 31);
        if(points > 20) points = 20;
        return points;
    }

    public int scoreMACD(String macdResult) {
        float macd = Float.parseFloat(macdResult);
        return macd >= 0.0f ? 0 : macd < -0.01f ? 20 : 10;
    }

    public Map<String, String> prepareRecommendation(int points){
        Map<String, String> output = new HashMap<>();

        if(points >= 70){
            output.put("description", "?");
            output.put("closePrice", "?");
            output.put("takeProfit", "?");
            output.put("stopLoss", "?");
            output.put("maxAmount", "5% of your balance");

        }
        else {
            output.put("description", "The probability of success is dangerously low. We recommend to do not invest " +
                    "in that moment. Please notice that sometime the best investment is not to invest.");
        }

        return output;
    }
}
