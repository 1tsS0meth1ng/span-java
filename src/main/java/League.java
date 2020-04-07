import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

public class League {


    private Collection<SoccerMatch> matches;
    private HashMap<String, SoccerTeam> leaderBoard = new HashMap<>();;

    /**Retrieves the player with a given name from the leader board.
     *
     * @param name the name of the soccer team being queried.
     * @return a soccer team that was retrieved from the leader board.
     */
    public SoccerTeam getTeam(String name) {
        return this.leaderBoard.get(name);
    }


    /**Adds a match to the match list while updating the leader board based on the outcomes.
     *
     * @param sm a soccer match being added.
     */
    public void addMatch(SoccerMatch sm) {

        if(sm.getWinner() != null){  //Checks whether the game was a draw of not.
            SoccerTeam smWinner = sm.getWinner();
            SoccerTeam smLoser = sm.getLoser();

            SoccerTeam teamWin = getTeam(smWinner.name);  //Checks if the winning team has been inserted before.
            SoccerTeam teamLose = getTeam(smLoser.name);  //Checks if the losing team has been added before.

            if (teamWin == null) {  //If the team hasn't been added add the team
                this.leaderBoard.put(smWinner.name, smWinner);
                teamWin = getTeam(smWinner.name);
            }
            teamWin.score += 3;  //Update the winners score

            if (teamLose == null) {  //If the losing team hasn't been added add the team
                this.leaderBoard.put(smLoser.name, smLoser);
                teamLose = getTeam(smWinner.name);
            }
            //This team gains no points
        }
        else {  //If the match was a draw
            SoccerTeam teamOne = getTeam(sm.getTeamOne().name);
            SoccerTeam teamTwo = getTeam(sm.getTeamTwo().name);

            if (teamOne == null) {   //Checks if team one has been added if not add them
                this.leaderBoard.put(sm.getTeamOne().name, sm.getTeamOne());
                teamOne = getTeam(sm.getTeamOne().name);
            }
            teamOne.score += 1;  //Add the default + 1 for a draw

            if (teamTwo == null) {  //Checks if team two has been added if not add them
                this.leaderBoard.put(sm.getTeamTwo().name, sm.getTeamTwo());
                teamTwo = getTeam(sm.getTeamTwo().name);
            }
            teamTwo.score += 1;  //Add the default + 1 for a draw
        }
    }


    /**Builds a string which represents the rankings ladder
     *
     * @return a string which represents the rankings ladder
     */
    public String getLeaderBoardString() {
        //Setup a comparator to sort the leader board. First sorts by score, then name.
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

        //Sort the list created in the previous step
        List<Map.Entry<String, SoccerTeam>> mapEntries = new ArrayList<>(this.leaderBoard.entrySet());
        mapEntries.sort(valueComparator);

        LinkedList<Boolean> isTieWithPrevious = new LinkedList<>();
        LinkedList<Boolean> isTieWithNext = new LinkedList<>();
        int listSize = mapEntries.size();
        int i = 0;
        for (Map.Entry<String, SoccerTeam> entry: mapEntries){
            /*
              Compiles two lists checking a given result's previous value and/or next value if the same for formatting.
             */
            SoccerTeam team = entry.getValue();
            if (i > 0 && i < listSize-1) {  //Value is not at the beginning or the end of the list.
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
            if(i == 0) {  //Value is at the beginning of the list.
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
            if(i == listSize-1) {  //Value is at the end of the list.
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


        StringBuilder sb = new StringBuilder();  //Setup string builder
        int position = 1;
        int counter = 0;
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
        return sb.toString();
    }

    /**A helper method  to construct a string to display the team's score.
     *
     * @param position the position of the team for which the string is being constructed.
     * @param teamName the name of the team for which the string is being constructed.
     * @param score the score of the team for which the string is being constructed.
     * @param isTieWithNext is the next score going to be the same as the current one.
     * @param isTieWithPrevious was the previous score the same as the current one.
     * @param listSize the size of the list of teams.
     * @param counter the index of this team.
     * @return returns the formatted string for one team.
     */
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
