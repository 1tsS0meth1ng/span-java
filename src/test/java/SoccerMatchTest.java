import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Random;
import java.util.stream.Stream;

public class SoccerMatchTest {
    private static SoccerTeam teamOne, teamTwo;

    @BeforeAll
    static void setup() {
        teamOne = new SoccerTeam("Test 1");
        teamTwo = new SoccerTeam("Test 2");
    }

    @ParameterizedTest
    @MethodSource("teamOneWinIntIntProvider")
    public void testTeamOneWin(int scoreOne, int scoreTwo) {
        SoccerMatch tempSoccerMatch = new SoccerMatch(teamOne, teamTwo, scoreOne, scoreTwo);
        SoccerTeam tempSoccerTeam = tempSoccerMatch.getWinner();
        assertEquals(this.teamOne, tempSoccerTeam);
    }

    @ParameterizedTest
    @MethodSource("teamTwoWinIntIntProvider")
    public void testTeamTwoWin(int scoreOne, int scoreTwo) {
        SoccerMatch tempSoccerMatch = new SoccerMatch(teamOne, teamTwo, scoreOne, scoreTwo);
        SoccerTeam tempSoccerTeam = tempSoccerMatch.getWinner();

        assertEquals(this.teamTwo, tempSoccerTeam);
    }

    @ParameterizedTest
    @MethodSource("teamThreeWinIntIntProvider")
    public void testDraw(int scoreOne, int scoreTwo) {
        SoccerMatch tempSoccerMatch = new SoccerMatch(teamOne, teamTwo, scoreOne, scoreTwo);
        SoccerTeam tempSoccerTeam = tempSoccerMatch.getWinner();

        assertNull(tempSoccerTeam);
    }

    @ParameterizedTest
    @MethodSource("illegalScoreProvider")
    public void testInvalidScores(int scoreOne, int scoreTwo) {
        assertThrows(IllegalArgumentException.class, () -> {
            SoccerMatch tempSoccerMatch = new SoccerMatch(teamOne, teamTwo, scoreOne, scoreTwo);
        } );

    }


    static Stream<Arguments> teamOneWinIntIntProvider() {
        Random r = new Random();
        int upper = r.nextInt((10 - 6) + 1) + 6;
        int lower = r.nextInt(6);
        return Stream.of(
                arguments(100, 0),
                arguments(4, 2),
                arguments(5,4),
                arguments(2,0),
                arguments(upper, lower)
        );
    }

    static Stream<Arguments> teamTwoWinIntIntProvider() {
        Random r = new Random();
        int upper = r.nextInt((10 - 6) + 1) + 6;
        int lower = r.nextInt(6);
        return Stream.of(
                arguments(0, 200),
                arguments(1, 15),
                arguments(2,4),
                arguments(0, 10),
                arguments(lower, upper)
        );
    }

    static Stream<Arguments> teamThreeWinIntIntProvider() {
        Random r = new Random();
        int value = r.nextInt(11);
        return Stream.of(
                arguments(100, 0),
                arguments(4, 2),
                arguments(5,4),
                arguments(2,0),
                arguments(value, value)
        );
    }

    static Stream<Arguments> illegalScoreProvider() {
        return Stream.of(
                arguments(-1, 10),
                arguments(-10, -5),
                arguments(10,-5)
        );
    }
}