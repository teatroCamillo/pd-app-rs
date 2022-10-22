package pdapp.server.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String username;
    private Boolean hasCompletedRiskTest;
    private Boolean hasCompletedGamblingTest;

}
