package pdapp.server.service;

import org.springframework.stereotype.Service;
import pdapp.server.model.User;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private final User test = new User(UUID.randomUUID(), "test@mail.com", "test", "test");

    //test.getUserId();
    //temp DB
    private final Map<UUID, User> userMap = new HashMap<>();

    //userMap.put(test.getId(), test);



}
