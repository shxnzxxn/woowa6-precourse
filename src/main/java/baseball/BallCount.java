package baseball;

public enum BallCount {
    STRIKE, BALL, NOTHING;

    public static BallCount getResult(Ball playerBall, Ball answerBall) {
        if(playerBall.equals(answerBall))
            return STRIKE;

        if(!playerBall.isSameNumber(answerBall))
            return NOTHING;

        return BALL;
    }

    public boolean isNothing() {
        return this == NOTHING;
    }

    public boolean isStrike() {
        return this == STRIKE;
    }

    public boolean isBall(){
        return this == BALL;
    }
}
