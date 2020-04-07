/**
 * Represents a soccer team with a name and a score tracker.
 *
 * @author  Brendan Bath
 * @version 1.0
 * @since   2020-04-07
 */
public class SoccerTeam {
    public String name;
    public int score;

    /**
     * Creates a soccer team with a given name. Initializes the score tracker to 0.
     *
     * @param  name the name of the team
     */
    public SoccerTeam(String name) {
        this.name = name;
        this.score = 0;
    }
}
