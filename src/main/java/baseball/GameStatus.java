package baseball;

public enum GameStatus {
    PLAYING, END;

    public boolean isPlaying() {
        return this == PLAYING;
    }
}
