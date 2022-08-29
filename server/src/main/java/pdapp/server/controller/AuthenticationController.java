package pdapp.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pdapp.server.auth.JWTTokenHelper;
import pdapp.server.model.User;
import pdapp.server.requests.AuthenticationRequest;
import pdapp.server.responses.LoginResponse;
import pdapp.server.responses.UserInfo;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User)authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(user.getUsername());

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userObj.getUsername());

        return ResponseEntity.ok(userObj);
    }

}
