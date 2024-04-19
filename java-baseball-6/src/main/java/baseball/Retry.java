package baseball;

import java.util.Arrays;

public enum Retry {
    RETRY("1"), END("2"), ERROR("0");

    private String inputNumber;

    Retry(String inputNumber) {
        this.inputNumber = inputNumber;
    }


    public boolean want2Do() {
        return this == RETRY;
    }

    public static Retry valueOfInputNumber(String inputNumber){
        return Arrays.stream(values())
                .filter(value -> value.inputNumber.equals(inputNumber))
                .findAny()
                .orElse(null);
    }
}
