public abstract class view {

    private boolean run = true;

    public void run(){
        while (run){
            display();
        }
    }

    public void stop() {
        run = false;
    }

    public abstract void display();
}
