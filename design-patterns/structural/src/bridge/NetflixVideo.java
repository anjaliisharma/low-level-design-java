package bridge;

public class NetflixVideo extends Video{
    NetflixVideo(VideoProcessor videoProcessor) {
        super(videoProcessor);
    }

    @Override
    public void play(String videoFile){
        videoProcessor.process(videoFile);
    }
}
