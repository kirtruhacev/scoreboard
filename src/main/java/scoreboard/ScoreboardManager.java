package scoreboard;

import java.util.Collection;
import java.util.UUID;
import scoreboard.model.Match;
import scoreboard.model.Score;
import scoreboard.model.Team;

public interface ScoreboardManager {

    Match startMatch(Team homeTeam, Team awayTeam);

    void updateScore(Score score, UUID matchIdentifier);

    void finishMatch(UUID matchIdentifier);

    Collection<Match> getSummaryOfMatches();
}
