package pdapp.server.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pdapp.server.service.UserService;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserController {

    private UserService userService;

//
//    @GetMapping("/login")
//    public String isUser(){
//        return
//    }

}
