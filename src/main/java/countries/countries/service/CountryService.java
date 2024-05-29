package countries.countries.service;

import countries.countries.model.Player;
import countries.countries.model.Score;
import countries.countries.repository.PlayerRepository;
import countries.countries.repository.ScoreRepository;
import countries.countries.model.Country;
import countries.countries.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    private List<Country> remainingCountries;

    @PostConstruct
    public void init() {
        List<Country> countries = countryRepository.findAll();
        remainingCountries = new ArrayList<>(countries);
        Collections.shuffle(remainingCountries);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public Country getRandomCountry() {
        if (remainingCountries.isEmpty()) {
            resetRemainingCountries();
        }
        return remainingCountries.remove(remainingCountries.size() - 1);
    }

    private void resetRemainingCountries() {
        List<Country> countries = countryRepository.findAll();
        remainingCountries = new ArrayList<>(countries);
        Collections.shuffle(remainingCountries);
    }

    public Player createPlayer(String username) {
        Player player = new Player();
        player.setUsername(username);
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Score saveScore(Player player, boolean correct) {
        List<Score> scores = scoreRepository.findByPlayerId(player.getId());
        Score score;
        if (scores.isEmpty()) {
            score = new Score();
            score.setPlayer(player);
        } else {
            score = scores.get(0);
        }
        if (correct) {
            score.setCorrectAnswers(score.getCorrectAnswers() + 1);
            score.setScore(score.getScore() + 1);
        }
        return scoreRepository.save(score);
    }

    public List<Score> getScoresByPlayerId(Long playerId) {
        return scoreRepository.findByPlayerId(playerId);
    }
}
