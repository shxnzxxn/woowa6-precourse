package baseball;

import java.util.Objects;

public class Ball {
    private final Integer number;
    private final Integer position;

    public Ball(Integer number, Integer position) {
        this.number = number;
        this.position = position;
    }

    public boolean isEqualNumber(int number) {
        return this.number == number;
    }

    public boolean isSameNumber(Ball answerBall) {
        return answerBall.isEqualNumber(this.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return Objects.equals(number, ball.number) && Objects.equals(position, ball.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position);
    }
}
