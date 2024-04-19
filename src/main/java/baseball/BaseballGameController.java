package baseball;

import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballGameController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private BaseballGame baseballGame;

    public BaseballGameController(BaseballGame baseballGame){
        this.baseballGame = baseballGame;
    }


    public void start(){
        outputView.printStartMessage();
        run();
    }

    private void run(){
        do{
            baseballGame.start();
            baseballGame.chooseAnswerBalls();
            play();
            outputView.printEndMessage();
        }while(inputView.scanRetry().want2Do());

    }

    private void play(){
        while(baseballGame.isPlaying()) {
            Balls playerBalls = inputView.scanBalls();
            Result result = baseballGame.getResult(playerBalls);
            outputView.printResult(result);
        }
    }
}
