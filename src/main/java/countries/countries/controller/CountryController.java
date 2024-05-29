package countries.countries.controller;

import countries.countries.model.Country;
import countries.countries.model.Player;
import countries.countries.model.Score;
import countries.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/start")
    public ResponseEntity<?> getRandomCountry(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        Optional<Player> playerOptional = countryService.getPlayerByUsername(username);
        if (playerOptional.isEmpty()) {
            countryService.createPlayer(username);
        }
        Country country = countryService.getRandomCountry();
        return ResponseEntity.ok(country);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> guessCapital(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String guessedCapital = payload.get("capital");
        String username = payload.get("username");
        Map<String, String> response = new HashMap<>();
        Optional<Country> countryOptional = countryService.getCountryById(id);
        Optional<Player> playerOptional = countryService.getPlayerByUsername(username);
        if (countryOptional.isPresent() && playerOptional.isPresent()) {
            Country country = countryOptional.get();
            Player player = playerOptional.get();
            if (country.getCapital().equalsIgnoreCase(guessedCapital)) {
                response.put("result", "C'est exact ! Vous avez deviné la bonne capital");
                countryService.saveScore(player, true);
                return ResponseEntity.ok(response);
            } else {
                response.put("result", "Faux ! La capital correcte est " + country.getCapital());
                countryService.saveScore(player, false);
                return ResponseEntity.ok(response);
            }
        } else {
            response.put("result", "Pays ou joueur introuvable.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/score/{username}")
    public ResponseEntity<?> getPlayerScores(@PathVariable String username) {
        Optional<Player> playerOptional = countryService.getPlayerByUsername(username);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            List<Score> scores = countryService.getScoresByPlayerId(player.getId());
            return ResponseEntity.ok(scores);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Joueur introuvable.");
        }
    }
}
