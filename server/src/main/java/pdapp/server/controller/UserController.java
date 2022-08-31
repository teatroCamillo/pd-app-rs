package pdapp.server.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdapp.server.model.User;
import pdapp.server.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String getString(){
        return "hello get stirng meethod";
    }

}
