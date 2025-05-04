package scoreboard;

import java.util.Objects;

public class Score {

    private final int home;
    private final int away;

    private Score(int home, int away) {
        this.home = home;
        this.away = away;
    }

    public static Score initialScore() {
        return new Score(0, 0);
    }

    public static Score createNew(int homeTeam, int awayTeam) {
        return new Score(homeTeam, awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(home, away);
    }

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
