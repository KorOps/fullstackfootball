package korops.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
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
        Player player= new Player(1, "Jamie");
        PlayerRepo.save(player);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                 {
                                    "id": "1",
                                     "name": "Jamie"
                                  
                                 }
                                ]
                                """
                ));
    }

    @Test
    void getById_shouldReturnPlayerWhenCalledWithId() throws Exception {
        Player player= new Player(1, "Jamie");
        PlayerRepo.save(player);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/{id}", 1)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                 {
                                    "id": "1",
                                     "name": "Jamie"
                                  
                                 }
                                ]
                                """
                ));
    }

    @Test
    void savePlayer_newPlayerShouldBeSavedAndReturnedWhenCalled() throws Exception{
        String requestBody = """
            {
                "name": "Jamie"
            }
            """;

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String savedPlayerId = result.getResponse().getContentAsString();
        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + savedPlayerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "id":""" + savedPlayerId + """,
                        "name": "Jamie"
                    }
                    """
                ));
    }

    @Test
    void updatePlayer_ableToEditExistingPlayerWhenCalledAndThenReturn() throws Exception {
            Player player = new Player(1, "Jamie");
            Player savedPlayer = PlayerRepo.save(player);

            String updatedName = "UpdatedJamie";
            String requestBody = """
            {
                "name": "%s"
            }
            """.formatted(updatedName);

            mvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/" + savedPlayer.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + savedPlayer.getId()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(
                            """
                            {
                                "id": "%s",
                                "name": "%s"
                            }
                            """.formatted(savedPlayer.getId(), updatedName)
                    ));
    }

    @Test
    void deletePlayer_ableToDeletePlayerWhenCalled() throws Exception {
        Player player = new Player(1, "Jamie");
        Player savedPlayer = PlayerRepo.save(player);

        mvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + savedPlayer.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + savedPlayer.getId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound()
                );
    }


    @Test
    void findPlayerByFirstnameAndLastname() {
        Player player= new Player(1, "Jamie");
        PlayerRepo.save(player);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/find/Jamie"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                 {
                                    "id": "1",
                                     "name": "Jamie"
                                  
                                 }
                                ]
                                """
                ));
    }
}