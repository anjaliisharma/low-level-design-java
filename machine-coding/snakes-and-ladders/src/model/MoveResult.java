package model;

public class MoveResult {
    private final int finalPosition;
    private final boolean hitObstacle;
    private final String obstacleDescription; // e.g. "Snake! 98 -> 98" - null if none

    public MoveResult(int finalPosition, boolean hitObstacle, String obstacleDescription) {
        this.finalPosition = finalPosition;
        this.hitObstacle = hitObstacle;
        this.obstacleDescription = obstacleDescription;
    }

    public int getFinalPosition() { return finalPosition; }
    public boolean hitObstacle() { return hitObstacle; }
    public String getObstacleDescription() { return obstacleDescription; }
}
