package scoreboard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import scoreboard.exceptions.InvalidScoreException;
import scoreboard.model.Score;

class ScoreTest {

    public static Stream<Arguments> negativeScoreProvider() {
        return Stream.of(Arguments.of(0, -1), Arguments.of(-1, 0), Arguments.of(-1, -1));
    }

    @ParameterizedTest
    @MethodSource("negativeScoreProvider")
    void shouldThrowIllegalArgumentExceptionWhenNegativeScore(int home, int away) {
        assertThrows(InvalidScoreException.class, () -> Score.createNew(home, away),
                     "Score can not be negative: home %s away %s ".formatted(String.valueOf(home),
                                                                             String.valueOf(away)));
    }
}