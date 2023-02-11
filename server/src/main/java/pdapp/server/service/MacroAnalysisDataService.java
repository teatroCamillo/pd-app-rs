package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pdapp.server.util.Constant.*;

@Service
@Slf4j
public class MacroAnalysisDataService {

    private final OutcomeService os;

    @Autowired
    public MacroAnalysisDataService(final OutcomeService os){
        this.os = os;
    }

    public Map<String, String> macroStrategy(Map<String, Map<String, List<Float>>> gdp,
                                             Map<String, Map<String, List<Float>>> inf){
        Map<String, String> output = new HashMap<>();

        Map<String, String> comp = scoreLatestQGDP(gdp);
        Map<String, String> infComp = compareInflation(inf);
        int macroPoints = Integer.parseInt(comp.get(GDP_POINTS)) + Integer.parseInt(infComp.get(INF_POINTS));
        output.putAll(comp);
        output.putAll(infComp);
        output.put(MACRO_POINTS, String.valueOf(macroPoints));

        return output;
    }

    /**
     * @description: Pull out the last quarterly GDP growth value and set the points score.
     * @param gdp
     * @return Map<String, String> output
     */
    private Map<String, String> scoreLatestQGDP(Map<String, Map<String, List<Float>>> gdp){
        Map<String, String> output = new HashMap<>();
        Map<String, List<Float>> eaGDP = new HashMap<>(gdp.get("ea"));
        List<Float> qGDPValues = new ArrayList<>();
        String yearNow = String.valueOf(LocalDate.now().getYear());
        String yearPrev = String.valueOf(LocalDate.now().getYear() - 1 );
        try {
            if(eaGDP.containsKey(yearNow)) qGDPValues.addAll(eaGDP.get(yearNow));
            else qGDPValues.addAll(eaGDP.get(yearPrev));
        }
        catch (Exception e){
            log.info(e.getMessage());
        }

        log.info(qGDPValues.toString());
        float qGDPLatest = qGDPValues.get(0);
        output.put(GDP_GROWTH_LATEST_Q, qGDPLatest + "%");

        /** 40 points for 2 macro indicator, 20 points each
         * GDP growth
         * Calculating by formula:
         */
        int points = os.scoreMacroGdpGrowth(qGDPLatest);
        output.put(GDP_POINTS, String.valueOf(points));
        os.setMacro(output);

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
        os.setMacro(output);

        return output;
    }

}
