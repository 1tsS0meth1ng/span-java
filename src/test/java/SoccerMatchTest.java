import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Random;
import java.util.stream.Stream;

public class SoccerMatchTest {
    private SoccerTeam teamOne = new SoccerTeam("Test 1"), teamTwo= new SoccerTeam("Test 2"),
            teamThree = new SoccerTeam("should be null");


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
    @MethodSource("teamDrawIntProvider")
    public void testDraw(int scoreOne, int scoreTwo) {
        SoccerMatch tempSoccerMatch = new SoccerMatch(teamOne, teamTwo, scoreOne, scoreTwo);
        SoccerTeam tempSoccerTeam = tempSoccerMatch.getWinner();

        //assertNull(tempSoccerTeam);
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
                Arguments.of(100, 0),
                Arguments.of(4, 2),
                Arguments.of(5,4),
                Arguments.of(2,0),
                Arguments.of(upper, lower)
        );
    }

    static Stream<Arguments> teamTwoWinIntIntProvider() {
        Random r = new Random();
        int upper = r.nextInt((10 - 6) + 1) + 6;
        int lower = r.nextInt(6);
        return Stream.of(
                Arguments.of(0, 200),
                Arguments.of(1, 15),
                Arguments.of(2,4),
                Arguments.of(0, 10),
                Arguments.of(lower, upper)
        );
    }

    static Stream<Arguments> teamDrawIntProvider() {
        Random r = new Random();
        int value = r.nextInt(101);
        return Stream.of(
                Arguments.of(100,1000),
                Arguments.of(4, 4),
                Arguments.of(5,5),
                Arguments.of(0,0),
                Arguments.of(value, value)
        );
    }

    static Stream<Arguments> illegalScoreProvider() {
        return Stream.of(
                Arguments.of(-1, 10),
                Arguments.of(-10, -5),
                Arguments.of(10,-5)
        );
    }
}