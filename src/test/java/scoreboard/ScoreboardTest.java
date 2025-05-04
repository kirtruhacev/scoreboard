package scoreboard;

import static java.util.UUID.randomUUID;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.time.Instant;
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

        var newScore = Score.createNew(1, 0);
        scoreboard.updateScore(newScore, match.getIdentifier());

        assertEquals(newScore, scoreboard.getMatchScore(match.getIdentifier()));
    }

    @Test
    void shouldThrowMatchNotFoundExceptionWhenUpdateMatchScoreForNonExistingMatch() {
        var newScore = Score.createNew(1, 0);

        assertThrows(MatchNotFoundException.class, () -> scoreboard.updateScore(newScore, randomUUID()));
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
        assertDoesNotThrow(() -> scoreboard.finishMatch(randomUUID()));
    }

    @Test
    void shouldListAllMatchesOrderedByTheTotalScore() {
        var firstMatch = startMatch();
        var secondMatch = startMatch();
        scoreboard.updateScore(Score.createNew(1, 0), secondMatch.getIdentifier());

        var summaryOfMatches = scoreboard.getSummaryOfMatches();

        assertEquals(secondMatch.getIdentifier(), summaryOfMatches.get(0).getIdentifier());
        assertEquals(firstMatch.getIdentifier(), summaryOfMatches.get(1).getIdentifier());
    }

    @Test
    void shouldListAllMatchesOrderedByTheMostRecentStartTimeWhenTotalScoreIsEqual() {
        var firstMatch = startMatch();
        var lastMatch = startMatch();

        var summaryOfMatches = scoreboard.getSummaryOfMatches();

        assertEquals(lastMatch.getIdentifier(), summaryOfMatches.get(0).getIdentifier());
        assertEquals(firstMatch.getIdentifier(), summaryOfMatches.get(1).getIdentifier());
    }

    @Test
    void shouldReturnSummaryOfMatchesInExpectedOrder() {
        var now = Instant.now();

        var mexicoCanadaMatch = startMatch("Mexico", "Canada");
        scoreboard.updateScore(Score.createNew(0, 5), mexicoCanadaMatch.getIdentifier());
        mockStartTimeByAddingNanoSecondsToTime(mexicoCanadaMatch, now, 1);

        var spainBrazilMatch = startMatch("Spain", "Brazil");
        scoreboard.updateScore(Score.createNew(10, 2), spainBrazilMatch.getIdentifier());
        mockStartTimeByAddingNanoSecondsToTime(mexicoCanadaMatch, now, 2);

        var germanyFranceMatch = startMatch("Germany", "France");
        scoreboard.updateScore(Score.createNew(2, 2), germanyFranceMatch.getIdentifier());
        mockStartTimeByAddingNanoSecondsToTime(mexicoCanadaMatch, now, 3);

        var uruguayItalyMatch = startMatch("Uruguay", "Italy");
        scoreboard.updateScore(Score.createNew(6, 6), uruguayItalyMatch.getIdentifier());
        mockStartTimeByAddingNanoSecondsToTime(mexicoCanadaMatch, now, 4);

        var argentinaAustraliaMatch = startMatch("Argentina", "Australia");
        scoreboard.updateScore(Score.createNew(3, 1), argentinaAustraliaMatch.getIdentifier());
        mockStartTimeByAddingNanoSecondsToTime(mexicoCanadaMatch, now, 5);

        var summaryOfMatches = scoreboard.getSummaryOfMatches();

        assertEquals(uruguayItalyMatch.getIdentifier(), summaryOfMatches.get(0).getIdentifier());
        assertEquals(spainBrazilMatch.getIdentifier(), summaryOfMatches.get(1).getIdentifier());
        assertEquals(mexicoCanadaMatch.getIdentifier(), summaryOfMatches.get(2).getIdentifier());
        assertEquals(argentinaAustraliaMatch.getIdentifier(), summaryOfMatches.get(3).getIdentifier());
        assertEquals(germanyFranceMatch.getIdentifier(), summaryOfMatches.get(4).getIdentifier());
    }

    private static void mockStartTimeByAddingNanoSecondsToTime(Match mexicoCanadaMatch, Instant time, int nanosToAdd) {
        var mexicoCanadaMatchSpy = spy(mexicoCanadaMatch);
        when(mexicoCanadaMatchSpy.getStartTime()).thenReturn(time.plusNanos(nanosToAdd));
    }

    private Match startMatch() {
        return scoreboard.startMatch(Team.fromValue(randomString()), Team.fromValue(randomString()));
    }

    private Match startMatch(String homeTeam, String awayTeam) {
        return scoreboard.startMatch(Team.fromValue(homeTeam), Team.fromValue(awayTeam));
    }
}