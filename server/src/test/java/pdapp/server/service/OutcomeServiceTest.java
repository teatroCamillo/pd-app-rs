package pdapp.server.service;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static pdapp.server.util.Constant.*;
import static pdapp.server.util.Constant.MAX_AMOUNT_TO_INVEST;

class OutcomeServiceTest {

    @Test
    void calculateMacroInflation() {
        float eaInf = 9.2f;
        float usInf = 6.5f;

        OutcomeService os = new OutcomeService();

        int result = os.compareMacroInflation(eaInf, usInf);
        assertEquals(0, result);
    }

    @Test
    void prepareRecommendation70Test() {

        Map<String, String> expected = new HashMap<>();
        expected.put(REC_DESCRIPTION, "The probability of success is very high. " +
                "Approximate daily movement on that pair estimates around 80 - 120 pips. " +
                "The best risk to reward ratio in this case is 1:3. Based on that all we " +
                "are suggesting to apply the following assumptions:");
        expected.put(TAKE_PROFIT, "1.0300");
        expected.put(STOP_LOSS, "1.0100");
        expected.put(MAX_AMOUNT_TO_INVEST, "5% of your balance");

        OutcomeService os = new OutcomeService();

        Map<String, String> inputTech = new HashMap<>();
        inputTech.put(CURRENT_PRICE, "1.0150");
        os.setTech(inputTech);

        Map<String, String> result = os.prepareRecommendation(70);
        assertEquals(expected, result);
    }

    @Test
    void prepareRecommendation50Test() {

        Map<String, String> expected = new HashMap<>();
        expected.put(REC_DESCRIPTION, "The probability of success is around 50%. " +
                "Approximate daily movement on that pair estimates around 80 - 120 pips. " +
                "The best risk to reward ratio in this case is 1:2. Based on that all we " +
                "are suggesting to apply the following assumptions:");
        expected.put(TAKE_PROFIT, "1.0250");
        expected.put(STOP_LOSS, "1.0100");
        expected.put(MAX_AMOUNT_TO_INVEST, "2% of your balance");

        OutcomeService os = new OutcomeService();

        Map<String, String> inputTech = new HashMap<>();
        inputTech.put(CURRENT_PRICE, "1.0150");
        os.setTech(inputTech);

        Map<String, String> result = os.prepareRecommendation(60);
        assertEquals(expected, result);
    }

    @Test
    void prepareRecommendationLessThan50Test() {

        Map<String, String> expected = new HashMap<>();
        expected.put(REC_DESCRIPTION, "The probability of success is dangerously low. We recommend to do not invest " +
                "in that moment. Please notice that sometime the best investment is not investing.");

        OutcomeService os = new OutcomeService();

        Map<String, String> inputTech = new HashMap<>();
        inputTech.put(CURRENT_PRICE, "1.0150");
        os.setTech(inputTech);

        Map<String, String> result = os.prepareRecommendation(40);
        assertEquals(expected, result);
    }
}