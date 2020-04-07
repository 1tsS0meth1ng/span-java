import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class LeagueTest {

    private final String defaultLeaderBoard = "1. Tarantulas, 6 pts\n2. Lions, 5 pts\n3. FC Awesome, 1 pt\n" +
            "3. Snakes, 1 pt\n5. Grouches, 0 pts";
    private League league;

    @ParameterizedTest
    @MethodSource("streamTestFile")
    public void testAddMatch(SoccerMatch match) {
        this.league = new League();
        this.league.addMatch(match);
        SoccerTeam teamOne = this.league.getTeam(match.getTeamOne().name);
        assertNotNull(teamOne);
        SoccerTeam teamTwo = this.league.getTeam(match.getTeamTwo().name);
        assertNotNull(teamTwo);
    }

    @ParameterizedTest
    @MethodSource("streamLeaderBoardFromFile")
    public void testLeaderBoardString(Collection<SoccerMatch> matches) {
        this.league = new League();
        for (SoccerMatch sm: matches){
            league.addMatch(sm);
        }

        String testLeaderBoard = this.league.getLeaderBoardString();

        assertEquals(this.defaultLeaderBoard, testLeaderBoard);

    }

    static Stream<Arguments> streamTestFile() {
        Collection<Arguments> argList = new LinkedList<Arguments>();
        File file = new File("src/test/resources/SoccerLeagueTest.txt");
        try {

            if (file.canRead()){
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] splitValues = line.split(",");

                    String[] scoreNameOne = splitValues[0].split(" ");
                    String scoreOneString = scoreNameOne[scoreNameOne.length -1];
                    String teamOneName = String.join(" ", scoreNameOne);

                    String[] scoreNameTwo = splitValues[1].split(" ");
                    String scoreTwoString = scoreNameTwo[scoreNameTwo.length -1];
                    String teamTwoName = String.join(" ", scoreNameTwo);

                    int scoreOne = Integer.parseInt(scoreOneString);
                    int scoreTwo = Integer.parseInt(scoreTwoString);

                    SoccerMatch match = new SoccerMatch(new SoccerTeam(teamOneName), new SoccerTeam(teamTwoName),
                            scoreOne, scoreTwo);
                    argList.add(arguments(match));
                }
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return argList.stream();
    }

    static Stream<Arguments> streamLeaderBoardFromFile() {
        Arguments args = null;
        File file = new File("src/test/resources/SoccerLeagueTest.txt");
        Collection<SoccerMatch> soccerList = new LinkedList<>();
        try {

            if (file.canRead()){
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] splitValues = line.split(",");

                    ArrayList<String> scoreNameOne = new ArrayList<>(Arrays.asList(splitValues[0].split(" ")));
                    String scoreOneString = scoreNameOne.remove(scoreNameOne.size() -1);
                    String teamOneName = String.join(" ", scoreNameOne).trim();

                    ArrayList<String> scoreNameTwo = new ArrayList<>(Arrays.asList(splitValues[1].split(" ")));
                    String scoreTwoString = scoreNameTwo.remove(scoreNameTwo.size() -1);
                    String teamTwoName = String.join(" ", scoreNameTwo).trim();

                    int scoreOne = Integer.parseInt(scoreOneString);
                    int scoreTwo = Integer.parseInt(scoreTwoString);

                    SoccerMatch match = new SoccerMatch(new SoccerTeam(teamOneName), new SoccerTeam(teamTwoName),
                            scoreOne, scoreTwo);
                    soccerList.add(match);
                }
                args = arguments(soccerList);
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Stream.of(args);
    }

}

