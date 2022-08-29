package pdapp.server.requests;

import lombok.Getter;

@Getter
public class AuthenticationRequest {

    private String username;
    private String password;

}
