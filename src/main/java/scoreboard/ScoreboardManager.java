package scoreboard;

import java.util.Collection;
import java.util.UUID;

public interface ScoreboardManager {

    Match startMatch(Team homeTeam, Team awayTeam);

    void updateScore(Score score, UUID matchIdentifier);

    void finishMatch(UUID matchIdentifier);

    Collection<Match> getSummaryOfMatches();
}
