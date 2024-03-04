package korops.backend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    private final String BASE_URL = "/api/player";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PlayerRepo repo;

    @DirtiesContext
    @Test
    void getAllPlayers_shouldReturnListOfPlayersWhenCalled() throws Exception {
        Players player = new Players("1", "Jamie","Vardy", "England", 10, "1,50","right", "Striker","Leicester City");
        repo.save(player);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                 {
                                    "id": "1",
                                     "firstname": "Jamie",
                                     "lastname": "Vardy",
                                     "nationality": "England",
                                     "age": 10,
                                     "height": "1,50",
                                     "foot": "right",
                                     "position": "Striker",
                                     "team": "Leicester City"
                                 }
                                ]
                                """
                ));
    }

    @Test
    void getById_shouldReturnPlayerWhenCalledWithId() throws Exception {
        Players player= new Players("1", "Jamie","Vardy", "England", 10, "1,50","right", "Striker","Leicester City");
        repo.save(player);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/1", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                
                                 {
                                    "id": "1",
                                     "firstname": "Jamie",
                                     "lastname": "Vardy",
                                     "nationality": "England",
                                     "age": 10,
                                     "height": "1,50",
                                     "foot": "right",
                                     "position": "Striker",
                                     "team": "Leicester City"
                                 }
                                
                                """
                ));
    }

    @Test
    void savePlayer_newPlayerShouldBeSavedAndReturnedWhenCalled() throws Exception {

        String requestBody = """
        {
            "id": "1",
            "firstname": "Jamie",
            "lastname": "Vardy",
            "nationality": "England",
            "age": 10,
            "height": "1,50",
            "foot": "right",
            "position": "Striker",
            "team": "Leicester City"
        }
        """;

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(

                        """
                        {
                        "id": "1",
                "firstname": "Jamie",
                "lastname": "Vardy",
                "nationality": "England",
                "age": 10,
                "height": "1,50",
                "foot": "right",
                "position": "Striker",
                "team": "Leicester City"
        }
        """));
                    

    }

    @Test
    void updatePlayer_ableToEditExistingPlayerWhenCalledAndThenReturn() throws Exception {
        Players player = new Players("1", "Jamie", "Vardy", "England", 10, "1,50", "Right", "Striker", "Leicester City");
        Players savedPlayer = repo.save(player);

        String requestBody = """
        {
            "id": "1",
            "firstname": "Tony",
            "lastname": "Vardy",
            "nationality": "England",
            "age": 10,
            "height": "1,50",
            "foot": "right",
            "position": "Striker",
            "team": "Leicester City"
        }
    """.formatted(1, "Tony");

        mvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                            "id": "1",
                            "firstname": "Tony",
                            "lastname": "Vardy",
                            "nationality": "England",
                            "age": 10,
                            "height": "1,50",
                            "foot": "right",
                            "position": "Striker",
                            "team": "Leicester City"
                        }
                        """.formatted(1, "Tony")
                ));
    }




    @Test
    void deletePlayer_ableToDeletePlayerWhenCalled() throws Exception {
        Players player = new Players("1", "Jamie", "Vardy", "England", 10, "1,50", "Right", "Striker", "Leicester City");
        repo.save(player);

        mvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/"+ 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));

    }



    @Test
    void findPlayerByFirstnameAndLastname_ableToReturnPlayerByFirstnameAndLastnameWhenCalled() throws Exception{
        Players player= new Players("1", "Jamie","Vardy", "England", 10, "1,50","Right", "Striker","Leicester City");
        repo.save(player);

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/find/Jamie/Vardy"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                
                                 {
                                    "id": "1",
                                     "firstname": "Jamie",
                                     "lastname": "Vardy",
                                     "nationality": "England",
                                     "age": 10,
                                     "height": "1,50",
                                     "foot": "Right",
                                     "position": "Striker",
                                      "team": "Leicester City"
                                  
                                 }
                                
                                """
                ));
    }
}