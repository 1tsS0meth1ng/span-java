import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainView extends View{

    League league;

    public MainView() {
        league = new League();
    }

    @Override
    public void display () {
        System.out.println("1. Import a file");
        System.out.println("2. Display the leader board");
        System.out.println("3. Quit");
    }

    @Override
    public void getExecuteFunction(int value) {
        switch (value){
            case 1:
                getFile();
                break;
            case 2:
                System.out.println(league.getLeaderBoardString());
                break;
            case 3:
                System.out.println("Thanks for coming");
                this.stop();
        }
    }

    private void getFile(){
        System.out.println("Please enter the path of the file:");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        File file = new File(path);
        try {
            if (file.canRead()){
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] splitValues = line.split(",");

                    ArrayList<String> scoreNameOne = new ArrayList(Arrays.asList(splitValues[0].split(" ")));
                    String scoreOneString = scoreNameOne.remove(scoreNameOne.size() -1);
                    String teamOneName = String.join(" ", scoreNameOne).trim();

                    ArrayList<String> scoreNameTwo = new ArrayList(Arrays.asList(splitValues[1].split(" ")));
                    String scoreTwoString = scoreNameTwo.remove(scoreNameTwo.size() -1);
                    String teamTwoName = String.join(" ", scoreNameTwo).trim();

                    int scoreOne = Integer.parseInt(scoreOneString);
                    int scoreTwo = Integer.parseInt(scoreTwoString);

                    SoccerMatch match = new SoccerMatch(new SoccerTeam(teamOneName), new SoccerTeam(teamTwoName),
                            scoreOne, scoreTwo);
                    league.addMatch(match);
                }
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Sorry that file could not be found.");
        }
    }
}
