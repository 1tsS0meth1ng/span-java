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
        boolean unsure = true;
        while(unsure) {
            Scanner in = new Scanner(System.in);
            System.out.println("Are you sure you want to exit?(y/n):");
            String input = in.nextLine().trim().toLowerCase();
            if(input.equals("y")){
                unsure = false;
                run = false;
                System.out.println("Have a fantastic day");
            }
            else if (input.equals("n")) {
                unsure = false;
            }
            else {
                System.out.println("Sorry that is an invalid input");
            }
        }
    }

    public abstract void display();

    public abstract void getExecuteFunction(int value);
}
