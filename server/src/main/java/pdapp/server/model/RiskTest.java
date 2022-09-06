package pdapp.server.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name = "risk_tests")
@Entity
@Data
public class RiskTest {

    @Id
    @GeneratedValue
    private UUID id;

    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;
    private String a6;
    private String a7;

    private String rtResult;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
