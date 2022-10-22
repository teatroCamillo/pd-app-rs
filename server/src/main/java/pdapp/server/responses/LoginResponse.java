package pdapp.server.responses;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String userId;

}
