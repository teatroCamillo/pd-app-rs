package pdapp.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MacroAnalysisDataService {


    public Map<String, String> macroStrategy(){

        Map<String, String> output = new HashMap<>();

        output.put("GDP", "245.23423");

        return output;
    }

}
