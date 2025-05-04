package scoreboard;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoreboard.exceptions.MatchNotFoundException;

class ScoreboardTest {

    private Scoreboard scoreboard;

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

    @Test
    void shouldFinishAMatchAndRemoveItFromScoreBoard() {
        var match = startMatch();

        assertFalse(scoreboard.getMatches().isEmpty());

        scoreboard.finishMatch(match.getIdentifier());

        assertTrue(scoreboard.getMatches().isEmpty());
    }

    @Test
    void shouldNotThrowExceptionWhenFinishingAMatchThatDoesNotExist() {
        assertDoesNotThrow(() -> scoreboard.finishMatch(UUID.randomUUID()));
    }

    private Match startMatch() {
        return scoreboard.startMatch(Team.fromValue(randomString()), Team.fromValue(randomString()));
    }
}