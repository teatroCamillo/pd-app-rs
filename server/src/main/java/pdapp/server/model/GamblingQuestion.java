package pdapp.server.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gambling_test_questions")
@Entity
@Data
public class GamblingQuestion {

    @Id
    private String id;
    private String question;
    private String name;
    private String a1;
    private String a2;
}
