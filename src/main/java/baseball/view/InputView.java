package baseball.view;

import baseball.Balls;
import baseball.Retry;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String INPUT_GUIDE = "숫자를 입력해주세요 : ";
    private final static String RETRY_GUIDE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static InputView instance;

    private InputView(){}

    public static InputView getInstance() {
        if(instance == null){
            instance = new InputView();
        }

        return instance;
    }

    public Balls scanBalls() {
        System.out.print(INPUT_GUIDE);
        String input = Console.readLine();
        return InputConverter.parseToBalls(input);
    }

    public Retry scanRetry() {
        System.out.println(RETRY_GUIDE);
        String input = Console.readLine();
        return InputConverter.parseToRetry(input);
    }
}
