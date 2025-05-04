package scoreboard;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreboardTest {

    private ScoreboardManager scoreboard;

    @BeforeEach
    void setUp() {
        this.scoreboard = new Scoreboard();
    }

    @Test
    void shouldStartANewMatchWithInitialScore() {
        var homeTeam = Team.fromValue(randomString());
        var awayTeam = Team.fromValue(randomString());
        var match = scoreboard.startMatch(homeTeam, awayTeam);

        assertEquals(Score.initialScore(), match.getScore());
    }
}