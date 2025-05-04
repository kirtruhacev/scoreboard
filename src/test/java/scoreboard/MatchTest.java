package scoreboard;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MatchTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreatingAMatchWithNullHomeTeam() {
        assertThrows(IllegalArgumentException.class, () -> Match.create(null, Team.fromValue(randomString())));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreatingAMatchWithNullAwayTeam() {
        assertThrows(IllegalArgumentException.class, () -> Match.create(Team.fromValue(randomString()), null));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenBothTeamsAreMissing() {
        assertThrows(IllegalArgumentException.class, () -> Match.create(null, null));
    }
}
