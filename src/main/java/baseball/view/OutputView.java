package baseball.view;

import baseball.Result;

public class OutputView {
    private final static String START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private final static String GAME_OVER_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static OutputView instance;

    private OutputView(){}


    public static OutputView getInstance() {
        if(instance == null){
            instance = new OutputView();
        }

        return instance;
    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printResult(Result result) {
        System.out.println(result.toString());
    }

    public void printEndMessage() {
        System.out.println(GAME_OVER_MESSAGE);
    }
}
