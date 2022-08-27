package pdapp.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pdapp.server.model.User;
import pdapp.server.repository.UserDetailsRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ServerApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@PostConstruct
	protected void init(){

		User user = new User();
		user.setUserName("tokio");
		user.setPassword(passwordEncoder.encode("999"));
		user.setEnabled(true);

		userDetailsRepository.save(user);

		User user1 = new User();
		user1.setUserName("berlin");
		user1.setPassword(passwordEncoder.encode("888"));
		user1.setEnabled(true);

		userDetailsRepository.save(user1);
	}


}
