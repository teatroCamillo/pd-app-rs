package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MacroAnalysisDataService {

    public Map<String, String> macroStrategy(Map<String, Map<String, List<Float>>> gdp){
        Map<String, String> output = new HashMap<>();

        Map<String, String> comp = compareLatestQGDP(gdp);
        output.putAll(comp);

        return output;
    }

    /**
     * @description: Pull out the last quarterly GDP growth value and set the points score.
     * @param gdp
     * @return Map<String, String> output
     */
    private Map<String, String> compareLatestQGDP(Map<String, Map<String, List<Float>>> gdp){
        Map<String, String> output = new HashMap<>();
        Map<String, List<Float>> eaGDP = new HashMap<>(gdp.get("ea"));
        List<Float> qValues = new ArrayList<>();
        try {
            qValues.addAll(eaGDP.get(String.valueOf(LocalDate.now().getYear())));
        }
        catch (Exception e){
            log.info("Exception: " + e.getMessage());
        }
        int size = qValues.isEmpty() ? 0 : qValues.size();

        if(size == 1 || qValues.isEmpty()){
            List<Float> qValues2 = eaGDP.get(String.valueOf(LocalDate.now().getYear()-1));
            if(size == 1) qValues.add(qValues2.get(qValues2.size()-1));
            else {
                qValues.add(qValues2.get(qValues2.size()-1));
                qValues.add(qValues2.get(qValues2.size()-2));
            }
        }

        output.put("latestQ", qValues.get(0).toString() + "%");
        output.put("previousQ", qValues.get(1).toString() + "%");

        /** 40 points for 2 macro indicator, 20 points each
         * GDP growth
         * Calculating by formula:
         */
        int points = (int)(qValues.get(0) * 10);
        if(points > 20) points = 20;
        if(points < 0) points = 0;
        output.put("points", String.valueOf(points));

        return output;
    }

}
