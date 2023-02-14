package pdapp.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class DataService {

    protected final OutcomeService os;

    @Autowired
    public DataService(final OutcomeService os){
        this.os = os;
    }

}
