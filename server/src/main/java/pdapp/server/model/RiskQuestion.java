package pdapp.server.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "risk_test_questions")
@Entity
@Data
public class RiskQuestion {

    @Id
    private String id;
    private String question;
    private String name;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;

}
