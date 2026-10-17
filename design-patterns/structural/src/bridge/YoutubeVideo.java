package bridge;

public class YoutubeVideo extends Video{
    YoutubeVideo(VideoProcessor videoProcessor) {
        super(videoProcessor);
    }

    @Override
    public void play(String videoFile) {
        videoProcessor.process(videoFile);
    }
}
