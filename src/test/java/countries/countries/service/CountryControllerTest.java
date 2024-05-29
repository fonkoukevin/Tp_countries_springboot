package countries.countries.service;

import countries.countries.controller.CountryController;
import countries.countries.model.Country;
import countries.countries.model.Player;
import countries.countries.service.CountryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    public void testGuessCapital() throws Exception {
        Long countryId = 1L;
        String username = "player1";
        String guessedCapital = "TestCapital";

        Player player = new Player();
        player.setUsername(username);
        Country country = new Country();
        country.setId(countryId);
        country.setName("TestCountry");
        country.setCapital("TestCapital");

        Mockito.when(countryService.getPlayerByUsername(username)).thenReturn(Optional.of(player));
        Mockito.when(countryService.getCountryById(countryId)).thenReturn(Optional.of(country));

        String jsonPayload = "{\"username\":\"player1\", \"capital\":\"TestCapital\"}";

        mockMvc.perform(post("/api/games/{id}", countryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());
    }
}

