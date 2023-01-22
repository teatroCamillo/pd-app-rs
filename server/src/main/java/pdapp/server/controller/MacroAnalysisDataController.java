package pdapp.server.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pdapp.server.service.MacroAnalysisDataService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class MacroAnalysisDataController {

    private final MacroAnalysisDataService mads;

    public MacroAnalysisDataController(){
        mads = new MacroAnalysisDataService();
    }

    @GetMapping("/{userId}/macro-analysis")
    public ResponseEntity<?> getMacroResults(){


        return new ResponseEntity<>(mads.macroStrategy(), HttpStatus.OK);
    }

}
