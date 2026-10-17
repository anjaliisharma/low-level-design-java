package template;

public class Football extends Game{
    @Override
    public void startGame() {
        System.out.println("starting Football");
    }

    @Override
    public void playGame() {
        System.out.println("Playing Football");
    }

    @Override
    public void stopGame() {
        System.out.println("stopping Football");
    }
}
