package baseball;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Balls {
    private List<Ball> balls = new ArrayList<>();

    public boolean isContains(int randomNumber) {
        return balls.stream().anyMatch((ball) -> ball.isEqualNumber(randomNumber));
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public boolean isFullBalls() {
        return balls.size() == BaseballGame.NUMBER_OF_MAX_BALL;
    }

    public Result calculateResult(Balls playerBalls) {
        // 여기서 balls는 answer의 balls
        Result result = new Result();
        balls.forEach((answerBall) -> {
            BallCount ballCount = playerBalls.calculateBallCount(answerBall);
            result.updateBallCount(ballCount);
        });

        return result;
    }

    private BallCount calculateBallCount(Ball answerBall) {
        // 여기에서 balls는 player의 balls
        Optional<BallCount> ballCount = balls.stream()
                .map((ball) -> (BallCount.getResult(ball, answerBall)))
                .filter((ballCnt -> ballCnt != BallCount.NOTHING))
                .findFirst();

        return ballCount.orElse(BallCount.NOTHING);
    }
}
