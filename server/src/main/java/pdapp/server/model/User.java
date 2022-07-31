package pdapp.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID userId;
    private String mail;
    private String userName;
    private String password;

    public UUID getUserId(){
        return userId;
    }

}
