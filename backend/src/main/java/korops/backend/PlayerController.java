package korops.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService service;

    @GetMapping
        public List<Players> getAllPlayers(){

        return service.getAllPlayers();
    }

    @GetMapping("/{id}")
        public Players getById(@PathVariable String id){

        return service.getPlayerById(id);
    }

    @PostMapping
        public Players savePlayer(@RequestBody Players player){

        return service.savePlayer(player);
    }

    @DeleteMapping("/{id}")
        public void deletePlayer(@PathVariable String id) {
        service.deletePlayer(id);
    }

    @GetMapping("/find/{firstname}/{lastname}")
        public Players findPlayerByFirstnameAndLastname(@PathVariable String firstname, @PathVariable String lastname){
        return service.findPlayerByFirstnameAndLastname(firstname, lastname);
    }

}
