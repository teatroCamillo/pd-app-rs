package pdapp.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "gambling_tests")
@Entity
@Data
public class GamblingTest {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, columnDefinition = "text[]")
    private String[] answers;

    @Column(nullable = false, length = 26)
    private String gtResult;

    @Column(nullable = false)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties
    private User user;
}
