public class SoccerMatch {

    private SoccerTeam teamOne, teamTwo;
    private int scoreOne, scoreTwo;

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

    public SoccerTeam getTeamOne() {
        return teamOne;
    }

    public SoccerTeam getTeamTwo() {
        return teamTwo;
    }


    public int getScoreOne() {
        return scoreOne;
    }

    public int getScoreTwo() {
        return scoreTwo;
    }

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
}
