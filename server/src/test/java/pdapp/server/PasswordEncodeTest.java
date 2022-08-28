package pdapp.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PasswordEncodeTest {

    @Test
    void genEncodedPassword(){
        BCryptPasswordEncoder bcyp = new BCryptPasswordEncoder();

        String result = bcyp.encode("999");
        System.out.println(result);

    }


}
