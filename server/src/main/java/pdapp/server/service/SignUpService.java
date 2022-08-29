package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdapp.server.ServerApplication;
import pdapp.server.model.User;
import pdapp.server.repository.UserDetailsRepository;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class SignUpService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    public User signUp(User user){

        User newUser = new User();
        newUser.setId(UUID.randomUUID());
        newUser.setUsername(user.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setMail(user.getMail());
        newUser.setAge(user.getAge());
        newUser.setCreated(new Date());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEnabled(true);

        userDetailsRepository.save(newUser);

        return newUser;
    }

}
