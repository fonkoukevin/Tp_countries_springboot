package countries.countries.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    private int score;
    private int correctAnswers;

    public Long getId() {
        return id;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}