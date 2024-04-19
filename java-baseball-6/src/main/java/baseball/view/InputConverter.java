package baseball.view;

import baseball.Ball;
import baseball.Balls;
import baseball.Retry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConverter {

    public static Balls parseToBalls(String input) {
        int position = 0;
        Balls balls = new Balls();
        // 검증은 생략
        List<Integer> numbers = Arrays.stream(input.split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();

        for (Integer number : numbers) {
            balls.addBall(new Ball(number, position++));
        }

        return balls;
    }

    public static Retry parseToRetry(String input) {
        return Retry.valueOfInputNumber(input);
    }
}
