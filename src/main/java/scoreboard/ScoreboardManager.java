package scoreboard;

import java.util.UUID;

public interface ScoreboardManager {

    Match startMatch(Team homeTeam, Team awayTeam);

    void updateScore(Score score, UUID matchIdentifier);

    Score getMatchScore(UUID matchIdentifier);
}
