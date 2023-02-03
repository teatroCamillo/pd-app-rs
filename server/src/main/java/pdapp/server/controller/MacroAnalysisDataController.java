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
        try{
            Resource resource = new ClassPathResource("/static/GDP-growth-2020-22-Q.json");
            File file = resource.getFile();
            gdp = om.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(mads.macroStrategy(gdp), HttpStatus.OK);
    }

}
