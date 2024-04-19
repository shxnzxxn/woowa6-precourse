package baseball;

import java.util.List;

public class Result {
    public static final String NOTHING = "낫싱";
    private Integer ball = 0;
    private Integer strike = 0;

    public void updateBallCount(BallCount ballCount) {
        if(ballCount.isStrike())
            strike++;

        if(ballCount.isBall())
            ball++;
    }

    public boolean isAllStrike() {
        return strike == BaseballGame.NUMBER_OF_MAX_BALL;
    }

    @Override
    public String toString() {
        if(isNothing()) return NOTHING;

        StringBuilder sb = new StringBuilder();
        sb.append(getBall()).append(getStrike());

        return sb.toString();
    }

    private boolean isNothing() {
        return strike == 0 && ball == 0;
    }

    private String getStrike() {
        if(strike == 0)
            return "";

        return strike + "스트라이크";
    }

    private String getBall() {
        if(ball == 0)
            return "";

        return ball + "볼";
    }
}
