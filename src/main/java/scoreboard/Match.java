package scoreboard;

import java.util.UUID;

public class Match {

    private final Team homeTeam;
    private final Team awayTeam;
    private Score score;
    private final UUID identifier;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = new Score(0, 0);
        this.identifier = UUID.randomUUID();
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

    public UUID getIdentifier() {
        return identifier;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
