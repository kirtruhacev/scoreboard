package scoreboard;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import scoreboard.exceptions.InvalidMatchException;
import scoreboard.exceptions.InvalidTeamException;
import scoreboard.model.Match;
import scoreboard.model.Team;

public class MatchTest {

    public static Stream<Arguments> invalidTeamNameProvider() {
        return Stream.of(Arguments.of(null, Team.fromValue(randomString())),
                         Arguments.of(Team.fromValue(randomString()), null), Arguments.of(null, null));
    }

    @ParameterizedTest
    @MethodSource("invalidTeamNameProvider")
    void shouldThrowIllegalArgumentExceptionWhenCreatingAMatchWithNullHomeTeam(Team homeTeam, Team awayTeam) {
        assertThrows(InvalidTeamException.class, () -> Match.create(homeTeam, awayTeam));
    }

    @Test
    void shouldThrowInvalidMatchExceptionWhenCreatingAMatchWithSameTeams() {
        var homeTeam = Team.fromValue(randomString());

        assertThrows(InvalidMatchException.class, () -> Match.create(homeTeam, homeTeam));
    }

    @Test
    void shouldThrowInvalidMatchExceptionWhenCreatingAMatchWithTeamsWithTheSameName() {
        var teamName = randomString();
        var awayTeam = Team.fromValue(teamName);
        var homeTeam = Team.fromValue(teamName);

        assertThrows(InvalidMatchException.class, () -> Match.create(homeTeam, awayTeam));
    }
}
