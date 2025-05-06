package scoreboard.model;

import java.util.Objects;
import nva.commons.core.JacocoGenerated;
import scoreboard.exceptions.InvalidScoreException;

public class Score {

    protected static final String NEGATIVE_SCORE_MESSAGE = "Score can not be negative: home %s away %s";
    private final int home;
    private final int away;

    private Score(int home, int away) {
        if (home < 0 || away < 0) {
            throw new InvalidScoreException(
                NEGATIVE_SCORE_MESSAGE.formatted(String.valueOf(home), String.valueOf(away)));
        }
        this.home = home;
        this.away = away;
    }

    public static Score initialScore() {
        return new Score(0, 0);
    }

    public static Score createNew(int homeTeam, int awayTeam) {
        return new Score(homeTeam, awayTeam);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(home, away);
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Score score)) {
            return false;
        }
        return home == score.home && away == score.away;
    }

    public int sum() {
        return home + away;
    }
}
