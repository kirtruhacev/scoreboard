package scoreboard;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;
import static scoreboard.Score.initialScore;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Match {

    private final Team homeTeam;
    private final Team awayTeam;
    private final UUID identifier;
    private final Instant startTime;
    private Score score;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = initialScore();
        this.startTime = now();
        this.identifier = randomUUID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getAwayTeam(), getIdentifier(), getStartTime(), getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Match match)) {
            return false;
        }
        return Objects.equals(getHomeTeam(), match.getHomeTeam()) &&
               Objects.equals(getAwayTeam(), match.getAwayTeam()) &&
               Objects.equals(getIdentifier(), match.getIdentifier()) &&
               Objects.equals(getStartTime(), match.getStartTime()) && Objects.equals(getScore(), match.getScore());
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public int getTotalScore() {
        return score.sum();
    }

    public Instant getStartTime() {
        return startTime;
    }
}
