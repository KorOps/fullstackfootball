package korops.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepo repo;

    public List<Players> getAllPlayers() {
        return repo.findAll();
    }

    public Players getPlayerById(String id) {
        return repo.findById(id)
                .orElseThrow(() ->new RuntimeException("Not Found"));

    }

    public Players savePlayer(Players player){
        return repo.save(player);
        }

    public Players updatePlayer(String id, Players updatedPlayer) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Player Not Found");
        }
        return repo.save(updatedPlayer);
    }

    public void deletePlayer(String id) {
        repo.deleteById(id);}


    public Players findPlayerBy(String id) {
        return repo.findPlayerById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Players findPlayerByFirstnameAndLastname(String fn, String ln) {
        return repo.findPlayerByFirstnameAndLastname(fn, ln)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
