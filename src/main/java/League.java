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

        LinkedList<Boolean> isTieWithPrevious = new LinkedList<>();
        LinkedList<Boolean> isTieWithNext = new LinkedList<>();
        int listSize = mapEntries.size();
        int i = 0;
        for (Map.Entry<String, SoccerTeam> entry: mapEntries){
            SoccerTeam team = entry.getValue();
            if (i > 0 && i < listSize-1) {
                SoccerTeam next = mapEntries.get(i+1).getValue();
                SoccerTeam previous = mapEntries.get(i-1).getValue();

                if (team.score == previous.score) {
                    isTieWithPrevious.add(true);
                }
                else {
                    isTieWithPrevious.add(false);
                }

                if (team.score == next.score) {
                    isTieWithNext.add(true);
                }
                else {
                    isTieWithNext.add(false);
                }
            }
            if(i == 0) {
                if(i == listSize-1) {
                    isTieWithNext.add(false);

                }
                else {
                    SoccerTeam next = mapEntries.get(i+1).getValue();
                    if (team.score == next.score) {
                        isTieWithNext.add(true);
                    }
                    else {
                        isTieWithNext.add(false);
                    }
                }
                isTieWithPrevious.add(false);
            }
            if(i == listSize-1) {
                if(i == 0) {
                    isTieWithPrevious.add(false);

                }
                else {
                    SoccerTeam previous = mapEntries.get(i-1).getValue();
                    if (team.score == previous.score) {
                        isTieWithPrevious.add(true);
                    }
                    else {
                        isTieWithPrevious.add(false);
                    }
                }
                isTieWithNext.add(false);
            }
            i++;
        }


        StringBuilder sb = new StringBuilder();
        int position = 1;
        int counter = 0;
        System.out.println(isTieWithNext.size());
        System.out.println(isTieWithPrevious.size());
        for(Map.Entry<String, SoccerTeam> entry: mapEntries) {
            SoccerTeam current = entry.getValue();
            boolean isNextTie = isTieWithNext.get(counter);
            boolean isPrevTie = isTieWithPrevious.get(counter);

            sb.append(buildLadderLine(position, current.name, current.score, isNextTie, isPrevTie, mapEntries.size(), counter));
            counter++;
            if(!isNextTie) {
                position = counter + 1;
            }
        }

        if(sb.length() == 0) {
            sb.append("No matches have been recorded");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    String buildLadderLine(int position, String teamName, int score, boolean isTieWithNext, boolean isTieWithPrevious,
                           int listSize , int counter) {
        StringBuilder sb = new StringBuilder();
        sb.append(position);
        sb.append(". ");
        sb.append(teamName);
        sb.append(", ");
        sb.append(score);
        sb.append(" pt");
        if(!isTieWithNext && !isTieWithPrevious) {
            sb.append('s');
        }
        if (counter < listSize-1) {
            sb.append('\n');
        }
        return sb.toString();
    }
}
