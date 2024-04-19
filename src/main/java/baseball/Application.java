package baseball;
import camp.nextstep.edu.missionutils.Console;

import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BaseballGameController gameController = new BaseballGameController(new BaseballGame());
        gameController.start();
    }
}
