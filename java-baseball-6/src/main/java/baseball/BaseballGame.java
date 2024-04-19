package baseball;

import camp.nextstep.edu.missionutils.Randoms;

public class BaseballGame {
    public static final Integer NUMBER_OF_MAX_BALL = 3;
    private Balls answerBalls;
    private GameStatus gameStatus;



    public void chooseAnswerBalls() {

        int position = 0;
        do{
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if(!answerBalls.isContains(randomNumber)){
                answerBalls.addBall(new Ball(randomNumber, position++));
            }
        }while(!answerBalls.isFullBalls());
    }

    public Result getResult(Balls playerBalls) {
        Result result = answerBalls.calculateResult(playerBalls);
        // 여기서 3s가 나온다면, GameStatus를 end로 바꾼다.
        if(result.isAllStrike())
            gameStatus = GameStatus.END;
        return result;
    }

    public boolean isPlaying() {
        return gameStatus.isPlaying();
    }

    public void start() {
        answerBalls = new Balls();
        gameStatus = GameStatus.PLAYING;
    }
}
