package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdapp.server.model.User;
import pdapp.server.service.SignUpService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
@Slf4j
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user){

        User savedUsr = signUpService.signUp(user);

        //TODO: what should be returned? Cuz now is returned whole object with password.
        return savedUsr != null ? ResponseEntity.ok(savedUsr) : (ResponseEntity<?>) ResponseEntity.badRequest();
    }
}
