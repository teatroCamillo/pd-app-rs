package pdapp.server.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name = "RISK_TEST")
@Entity
@Data
public class RiskTest {

    @Id
    @GeneratedValue
    private UUID id;

    
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private String q6;
    private String q7;

    private String rtResult;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date createdAt;

}
