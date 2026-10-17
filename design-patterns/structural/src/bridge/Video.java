package bridge;

public abstract class Video {
    protected VideoProcessor videoProcessor;

    Video(VideoProcessor videoProcessor){

        this.videoProcessor=videoProcessor;
    }

    public abstract void play(String videoFile);
}
