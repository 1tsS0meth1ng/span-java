/**Represents a soccer match between two soccer teams, along with their scores from the outcome.
 *
 * @author  Brendan Bath
 * @version 1.0
 * @since   2020-04-07
 */
public class SoccerMatch {

    private SoccerTeam teamOne, teamTwo;
    private int scoreOne, scoreTwo;

    /**Creates a soccer team after checking whether the scores are both > 0.
     * If the scores are not > 0 the IllegalArgumentException is thrown.
     *
     * @param teamOne the first team.
     * @param teamTwo the second team.
     * @param scoreOne the first outcome score.
     * @param scoreTwo the second outcome score
     */
    public SoccerMatch(SoccerTeam teamOne, SoccerTeam teamTwo, int scoreOne, int scoreTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        if(scoreOne >= 0 && scoreTwo >= 0) {
            this.scoreOne = scoreOne;
            this.scoreTwo = scoreTwo;
        }
        else {
            throw new IllegalArgumentException("None of the scores may be negative");
        }
    }

    /**Returns the winner based on the results of the games.
     *
     * @return the winning team. Or in case of a draw returns null.
     */
    public SoccerTeam getWinner() {
        SoccerTeam winner = null;

        if (this.scoreOne > this.scoreTwo) {
            winner = this.teamOne;
        }
        else if (this.scoreOne < this.scoreTwo) {
            winner = this.teamTwo;
        }

        return winner;
    }

    /**Returns the loser based on the results of the games.
     *
     * @return the losing team. Or in case of a draw returns null.
     */
    public SoccerTeam getLoser() {
        SoccerTeam loser = null;

        if (this.scoreOne < this.scoreTwo) {
            loser = this.teamOne;
        }
        else if (this.scoreOne > this.scoreTwo) {
            loser = this.teamTwo;
        }

        return loser;
    }

    /**gets the first team.
     *
     * @return an instance of team representing a soccer team.
     */
    public SoccerTeam getTeamOne() {
        return teamOne;
    }

    /**gets the second team.
     *
     * @return an instance of team representing a soccer team.
     */
    public SoccerTeam getTeamTwo() {
        return teamTwo;
    }
}
