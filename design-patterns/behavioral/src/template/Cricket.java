package template;

public class Cricket extends Game{
    @Override
    public void startGame() {
        System.out.println("starting Cricket");
    }

    @Override
    public void playGame() {
        System.out.println("Playing Cricket");
    }

    @Override
    public void stopGame() {
        System.out.println("stopping Cricket");
    }
}
