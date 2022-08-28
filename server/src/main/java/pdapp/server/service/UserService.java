package pdapp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pdapp.server.model.User;
import pdapp.server.repository.UserDetailsRepository;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Start loadUserByUsername");
        User user = userDetailsRepository.findByUserName(username);
        if(null == user){
            throw new UsernameNotFoundException("User not found with userName: " + username);
        }
        return user;
    }
}
