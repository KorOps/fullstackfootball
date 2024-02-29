package korops.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfiguration
class PlayerControllerTest {

    private final String BASE_URL = "/api/player";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepo repo;

    @DirtiesContext
    @Test
    void getAllPlayers_shouldReturnListOfPlayersWhenCalled() throws Exception {
        Player player = new Player("1", "Jamie");
        repo.save(player);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                                           
                                                   [{"firstname": "Jamie",
                                                               "id": "1"
                                                           }]
                                         
                                """
                ));
        }

    @Test
    void getById_shouldReturnPlayerWhenCalledWithId() throws Exception {
        NewPlayer player = NewPlayer("name");
        String newPlayerAsJSON = objectMapper.writeValueAsString(player);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(BASE_URL))
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPlayerAsJSON)
        .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Player savePlayer = objectMapper.readValue(result.getResponse().getContentAsString(), Player.class);
        String PlayerAsJSON = objectMapper.writeValueAsString(savePlayer);
    }

    @Test
    void savePlayer() {
    }

    @Test
    void updatePlayer() {
    }

    @Test
    void deletePlayer() {
    }

    @Test
    void findPlayerByFirstnameAndLastname() {
    }
}