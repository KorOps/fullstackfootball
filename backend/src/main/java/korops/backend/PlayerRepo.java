package korops.backend;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlayerRepo {

    Map<String, Player> playerMap = new HashMap<>();

    public List<Player> getAllPlayers() {
        return new ArrayList<>(playerMap.values());
    }
}
