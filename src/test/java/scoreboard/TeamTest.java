package scoreboard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import scoreboard.exceptions.InvalidTeamException;
import scoreboard.model.Team;

class TeamTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreatingATeamWithNullName() {
        assertThrows(InvalidTeamException.class, () -> Team.fromValue(null));
    }
}