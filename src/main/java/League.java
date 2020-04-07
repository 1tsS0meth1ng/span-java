import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

public class League {

    private Collection<SoccerMatch> matches;
    private Map<String, SoccerTeam> leaderBoard;

    public League() {
        this.matches = new LinkedList<>();
        this.leaderBoard = new HashMap<>();
    }

    public SoccerTeam getTeam(String name) {
        return this.leaderBoard.get(name);
    }

    public void addMatch(SoccerMatch sm) {
        matches.add(sm);

        if(sm.getWinner() != null){
            SoccerTeam smWinner = sm.getWinner();
            SoccerTeam smLoser = sm.getLoser();

            SoccerTeam teamWin = getTeam(smWinner.name);
            SoccerTeam teamLose = getTeam(smLoser.name);

            if (teamWin == null) {
                this.leaderBoard.put(smWinner.name, smWinner);
                teamWin = getTeam(smWinner.name);
            }
            teamWin.score += 3;

            if (teamLose == null) {
                this.leaderBoard.put(smLoser.name, smLoser);
                teamLose = getTeam(smWinner.name);
            }
        }
        else {
            SoccerTeam teamOne = getTeam(sm.getTeamOne().name);
            SoccerTeam teamTwo = getTeam(sm.getTeamTwo().name);

            if (teamOne == null) {
                this.leaderBoard.put(sm.getTeamOne().name, sm.getTeamOne());
                teamOne = getTeam(sm.getTeamOne().name);
            }
            teamOne.score += 1;

            if (teamTwo == null) {
                this.leaderBoard.put(sm.getTeamTwo().name, sm.getTeamTwo());
                teamTwo = getTeam(sm.getTeamTwo().name);
            }
            teamTwo.score += 1;
        }
    }

    public String getLeaderBoardString() {
        Comparator<Map.Entry<String, SoccerTeam>> valueComparator = new Comparator<Map.Entry<String,SoccerTeam>>() {
            @Override
            public int compare(Map.Entry<String, SoccerTeam> e1, Map.Entry<String, SoccerTeam> e2) {
                Integer value = e1.getValue().score;
                Integer value2 = e2.getValue().score;
                int returnComparison = value.compareTo(value2) *-1;
                if( returnComparison == 0) {
                    returnComparison = e1.getValue().name.compareTo(e2.getValue().name);
                }
                return returnComparison;
            }
        };

        List<Map.Entry<String, SoccerTeam>> mapEntries = new ArrayList<>(this.leaderBoard.entrySet());
        mapEntries.sort(valueComparator);

        for(Map.Entry<String, SoccerTeam> entry: mapEntries) {
            System.out.println(entry.getValue().score);
            System.out.println(entry.getValue().name);
        }

        return "";
    }
}
