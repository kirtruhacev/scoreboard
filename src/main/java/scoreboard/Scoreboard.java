package scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import scoreboard.exceptions.MatchNotFoundException;

public class Scoreboard implements ScoreboardManager {

    protected static final String MATCH_NOT_EXIST_MESSAGE = "Match with identifier %s does not exist";
    private final List<Match> matches;

    private Scoreboard(List<Match> matches) {
        this.matches = matches;
    }

    public static Scoreboard initiate() {
        return new Scoreboard(new ArrayList<>());
    }

    @Override
    public Match startMatch(Team homeTeam, Team awayTeam) {
        var match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    @Override
    public void updateScore(Score score, UUID matchIdentifier) {
        var match = getMatchByIdentifier(matchIdentifier);
        if (match.isPresent()) {
            match.get().setScore(score);
        } else {
            throw new MatchNotFoundException(MATCH_NOT_EXIST_MESSAGE.formatted(matchIdentifier));
        }
    }

    @Override
    public Score getMatchScore(UUID matchIdentifier) {
        return getMatchByIdentifier(matchIdentifier).map(Match::getScore)
                   .orElseThrow(() -> new MatchNotFoundException(MATCH_NOT_EXIST_MESSAGE.formatted(matchIdentifier)));
    }

    public List<Match> getMatches() {
        return matches;
    }

    private Optional<Match> getMatchByIdentifier(UUID identifier) {
        return matches.stream().filter(match -> identifier.equals(match.getIdentifier())).findFirst();
    }
}
