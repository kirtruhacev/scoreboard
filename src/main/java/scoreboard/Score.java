package scoreboard;

import java.util.Objects;

public class Score {

    private final int home;
    private final int away;

    public Score(int home, int away) {
        this.home = home;
        this.away = away;
    }

    public static Score initialScore() {
        return new Score(0, 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHome(), getAway());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Score score)) {
            return false;
        }
        return getHome() == score.getHome() && getAway() == score.getAway();
    }

    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }
}
