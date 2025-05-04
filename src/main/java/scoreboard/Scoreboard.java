package scoreboard;

public class Scoreboard implements ScoreboardManager {

    @Override
    public Match startMatch(Team homeTeam, Team awayTeam) {
        return new Match(homeTeam, awayTeam);
    }
}
