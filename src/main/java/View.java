import java.util.Scanner;

public abstract class View {

    private boolean run = true;

    public void run() {
        Scanner in = new Scanner(System.in);
        int selection;
        while (run){
            System.out.println("--------------------------------------------------------------------");
            display();
            System.out.println("Enter in the number of your selection:");
            selection = in.nextInt();
            getExecuteFunction(selection);

        }
    }

    protected void stop() {
        run = false;
    }

    public abstract void display();

    public abstract void getExecuteFunction(int value);
}
