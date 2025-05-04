package scoreboard;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoreboard.exceptions.MatchNotFoundException;

class ScoreboardTest {

    private ScoreboardManager scoreboard;

    @BeforeEach
    void setUp() {
        this.scoreboard = Scoreboard.initiate();
    }

    @Test
    void shouldStartANewMatchWithInitialScore() {
        var match = startMatch();

        assertEquals(Score.initialScore(), match.getScore());
    }

    @Test
    void shouldUpdateMatchScore() {
        var match = startMatch();

        var newScore = new Score(1, 0);
        scoreboard.updateScore(newScore, match.getIdentifier());

        assertEquals(newScore, scoreboard.getMatchScore(match.getIdentifier()));
    }

    @Test
    void shouldThrowMatchNotFoundExceptionWhenUpdateMatchScoreForNonExistingMatch() {
        var newScore = new Score(1, 0);
        assertThrows(MatchNotFoundException.class,
                     () -> scoreboard.updateScore(newScore, UUID.randomUUID()));
    }

    private Match startMatch() {
        return scoreboard.startMatch(Team.fromValue(randomString()), Team.fromValue(randomString()));
    }
}