package template;

public abstract class Game {
    public abstract void startGame();
    public abstract void playGame();
    public abstract void stopGame();

    public final void play() {
        startGame();
        playGame();;
        stopGame();
    }

}
