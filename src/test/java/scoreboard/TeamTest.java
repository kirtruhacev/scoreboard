package scoreboard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class TeamTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreatingATeamWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> Team.fromValue(null));
    }
}