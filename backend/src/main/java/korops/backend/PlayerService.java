package korops.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepo repo;

    public List<Player> getAllPlayers() {
        return repo.getAllPlayers();

    }
}
