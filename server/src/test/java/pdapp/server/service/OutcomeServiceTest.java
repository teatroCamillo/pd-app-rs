package pdapp.server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static pdapp.server.util.Constant.*;
import static pdapp.server.util.Constant.MAX_AMOUNT_TO_INVEST;

class OutcomeServiceTest {

    private OutcomeService os;
    @BeforeEach
    void setup(){
        os = new OutcomeService();
    }

    @Test
    void calculateMacroInflation() {
        float eaInf = 9.2f;
        float usInf = 6.5f;
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

        Map<String, String> inputTech = new HashMap<>();
        inputTech.put(CURRENT_PRICE, "1.0150");
        os.setTech(inputTech);

        Map<String, String> result = os.prepareRecommendation(40);
        assertEquals(expected, result);
    }

    @Test
    void compareMacroGdpGrowthTest() {
        int expected0 = 0;
        int expected1 = 5;
        int expected2 = 17;
        int expected3 = 20;

        assertEquals(expected0, os.compareMacroGdpGrowth(0.1f, 2.7f));
        assertEquals(expected1, os.compareMacroGdpGrowth(6.1f, 1.1f));
        assertEquals(expected2, os.compareMacroGdpGrowth(23.6f, 6.6f));
        assertEquals(expected3, os.compareMacroGdpGrowth(23.6f, 3.6f));
    }

    @Test
    void compareMacroInflation() {
        int expected0 = 0;
        int expected1 = 11;
        int expected2 = 20;

        assertEquals(expected0, os.compareMacroInflation(9.2f, 6.5f));
        assertEquals(expected1, os.compareMacroInflation(2.1f, 13.1f));
        assertEquals(expected2, os.compareMacroInflation(1.6f, 25.6f));
    }
}