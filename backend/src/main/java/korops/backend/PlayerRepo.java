package korops.backend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlayerRepo extends MongoRepository<Players, String> {

    public Optional<Players> findPlayerById (String id);
    public Optional<Players> findPlayerByFirstnameAndLastname (String firstname,
                                                              String lastname);

}

