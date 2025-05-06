package scoreboard.model;

import static java.time.Instant.now;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static scoreboard.model.Score.initialScore;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import nva.commons.core.JacocoGenerated;
import scoreboard.exceptions.InvalidMatchException;
import scoreboard.exceptions.InvalidScoreException;
import scoreboard.exceptions.InvalidTeamException;

public class Match {

    protected static final String TEAM_MISSING_MESSAGE = "Teams can not be missing when initializing a match!";
    protected static final String NULL_SCORE_MESSAGE = "Score can not be null!";
    protected static final String SAME_TEAMS_MESSAGE = "Can not have the same teams in a match";
    private final Team homeTeam;
    private final Team awayTeam;
    private final UUID identifier;
    private final Instant startTime;
    private Score score;

    private Match(Team homeTeam, Team awayTeam) {
        if (isNull(homeTeam) || isNull(awayTeam)) {
            throw new InvalidTeamException(TEAM_MISSING_MESSAGE);
        }
        if (homeTeam.equals(awayTeam)) {
            throw new InvalidMatchException(SAME_TEAMS_MESSAGE);
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = initialScore();
        this.startTime = now();
        this.identifier = randomUUID();
    }

    public static Match create(Team homeTeam, Team awayTeam) {
        return new Match(homeTeam, awayTeam);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getAwayTeam(), getIdentifier(), getStartTime(), getScore());
    }

    @JacocoGenerated
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
        if (isNull(score)) {
            throw new InvalidScoreException(NULL_SCORE_MESSAGE);
        }
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
