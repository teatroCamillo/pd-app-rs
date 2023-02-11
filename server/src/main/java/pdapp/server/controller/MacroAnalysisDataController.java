package pdapp.server.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pdapp.server.service.MacroAnalysisDataService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class MacroAnalysisDataController {

    private final MacroAnalysisDataService mads;
    private final ObjectMapper om;

    @Autowired
    public MacroAnalysisDataController(MacroAnalysisDataService mads, ObjectMapper om){
        this.mads = mads;
        this.om = om;
    }

    @GetMapping("/{userId}/macro-analysis")
    public ResponseEntity<?> getMacroResults(){

        Map<String, Map<String, List<Float>>> gdp = new HashMap<>();
        Map<String, Map<String, List<Float>>> inf = new HashMap<>();
        try{
            Resource resourceGDP = new ClassPathResource("/static/GDP-growth-2020-22-Q.json");
            File fileGDP = resourceGDP.getFile();

            Resource resourceINF = new ClassPathResource("/static/inflation-rate.json");
            File fileINF = resourceINF.getFile();
            gdp = om.readValue(fileGDP, new TypeReference<>() {});
            inf = om.readValue(fileINF, new TypeReference<>() {});
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(mads.macroStrategy(gdp, inf), HttpStatus.OK);
    }

}
