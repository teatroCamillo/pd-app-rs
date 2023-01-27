package pdapp.server.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Data
@Scope("singleton")
public final class Outcome {

    private static Outcome instance;
    private Map<String, String> personalMap;

    private Outcome(){
        this.personalMap = new HashMap<>();
    }

    public static Outcome getInstance(){
        if(instance == null) instance = new Outcome();
        return instance;
    }
}
