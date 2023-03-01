package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pdapp.server.util.Constant.*;

@Service
@Slf4j
public class MacroAnalysisDataService extends DataService {

    public MacroAnalysisDataService(OutcomeService os) {
        super(os);
    }

    public Map<String, String> strategy(Map<String, Map<String, List<Float>>> gdp,
                                             Map<String, Map<String, List<Float>>> inf){
        Map<String, String> output = new HashMap<>();

        Map<String, String> gdpComp = scoreLatestQGDP(gdp);
        Map<String, String> infComp = compareInflation(inf);
        int macroPoints = Integer.parseInt(gdpComp.get(GDP_POINTS)) + Integer.parseInt(infComp.get(INF_POINTS));
        output.putAll(gdpComp);
        output.putAll(infComp);
        output.put(MACRO_POINTS, String.valueOf(macroPoints));

        return output;
    }

    /**
     * @description: Pull out the last quarterly GDP growth values and set the points score.
     * @param gdpSource
     * @return Map<String, String> output
     */
    private Map<String, String> scoreLatestQGDP(Map<String, Map<String, List<Float>>> gdpSource){
        Map<String, String> output = new HashMap<>();
        Map<String, List<Float>> eaGDP = new HashMap<>(gdpSource.get("ea"));
        Map<String, List<Float>> usGDP = new HashMap<>(gdpSource.get("us"));
        float qGdpEA = 0.0f;
        float qGdpUS = 0.0f;
        String yearNow = String.valueOf(LocalDate.now().getYear());
        String yearPrev = String.valueOf(LocalDate.now().getYear() - 1 );
        try {
            List<Float> qGdpEAList;
            if(eaGDP.containsKey(yearNow)){
                qGdpEAList = eaGDP.get(yearNow);
                qGdpEA = qGdpEAList.get(qGdpEAList.size() - 1);
            }
            else {
                qGdpEAList = eaGDP.get(yearPrev);
                qGdpEA = qGdpEAList.get(qGdpEAList.size() - 1);
            }

            List<Float> qGdpUSList;
            if(usGDP.containsKey(yearNow)){
                qGdpUSList = usGDP.get(yearNow);
                qGdpUS = qGdpUSList.get(qGdpUSList.size() - 1);
            }
            else {
                qGdpUSList = usGDP.get(yearPrev);
                qGdpUS = qGdpUSList.get(qGdpUSList.size() - 1);
            }
        }
        catch (Exception e){
            log.info(e.getMessage());
        }

        log.info(String.valueOf(qGdpEA));
        log.info(String.valueOf(qGdpUS));

        output.put(EA_GDP_GROWTH_LATEST_Q, qGdpEA + "%");
        output.put(US_GDP_GROWTH_LATEST_Q, qGdpUS + "%");

        /** 40 points for 2 macro indicator, 20 points each
         * GDP growth
         * Calculating by formula:
         */
        int points = os.compareMacroGdpGrowth(qGdpEA, qGdpUS);
        output.put(GDP_POINTS, String.valueOf(points));

        return output;
    }

    private Map<String, String> compareInflation(Map<String, Map<String, List<Float>>> inf){
        Map<String, String> output = new HashMap<>();
        Map<String, List<Float>> infEA = new HashMap<>(inf.get("ea"));
        Map<String, List<Float>> infUS = new HashMap<>(inf.get("us"));

        List<Float> eaValues = new ArrayList<>();
        List<Float> usValues = new ArrayList<>();
        String yearNow = String.valueOf(LocalDate.now().getYear());
        String yearPrev = String.valueOf(LocalDate.now().getYear() - 1 );
        try {
            if(infEA.containsKey(yearNow)) eaValues.addAll(infEA.get(yearNow));
            else eaValues.addAll(infEA.get(yearPrev));

            if(infUS.containsKey(yearNow))  usValues.addAll(infUS.get(yearNow));
            else usValues.addAll(infUS.get(yearPrev));
        }
        catch (Exception e){
            log.info(e.getMessage());
        }

        float eaV = eaValues.get(eaValues.size() - 1);
        float usV = usValues.get(usValues.size() - 1);
        output.put(EA_INF, String.valueOf(eaV));
        output.put(US_INF, String.valueOf(usV));

        int points = os.compareMacroInflation(eaV, usV);
        output.put(INF_POINTS, String.valueOf(points));

        return output;
    }

}
