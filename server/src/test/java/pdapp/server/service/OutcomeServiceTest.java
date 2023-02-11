package pdapp.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutcomeServiceTest {

    @Test
    void calculateMacroInflation() {


        float eaInf = 9.2f;
        float usInf = 6.5f;

        OutcomeService os = new OutcomeService();

        int result = os.compareMacroInflation(eaInf, usInf);
        assertEquals(0, result);

    }
}