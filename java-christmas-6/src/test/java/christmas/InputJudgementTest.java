package christmas;

import christmas.DTO.MenuDto;
import christmas.model.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class InputJudgementTest {
    private final InputJudgement inputJudgement = new InputJudgement();

    @Test
    @DisplayName("입력값이 null이면 안 된다.")
    void 입력값이_null일_때_judgeInputDateOfVisitFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable(null);
        assertThatThrownBy(() -> inputJudgement.judgeInputDateOfVisitFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("입력값이 비어있으면 안 된다.")
    void 입력값이_비어있을_때_judgeInputDateOfVisitFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("");
        assertThatThrownBy(() -> inputJudgement.judgeInputDateOfVisitFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("방문 날짜 입력이 숫자 형식이 아니면 안 된다.")
    void 날짜_형식이_올바르지_않을_때_judgeInputDateOfVisitFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("아");
        assertThatThrownBy(() -> inputJudgement.judgeInputDateOfVisitFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("방문 날짜 입력이 범위를 넘으면 안 된다.")
    void 날짜_형식이_범위를_넘을_때_judgeInputDateOfVisitFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("40");
        assertThatThrownBy(() -> inputJudgement.judgeInputDateOfVisitFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("방문 날짜 입력이 올바르면 입력값이 정수로 나온다.")
    void 날짜_입력이_올바를_때_judgeInputDateOfVisitFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("23");
        Integer inputDateOfVisit = inputJudgement.judgeInputDateOfVisitFormatIsValid(dateOfVisit);

        Assertions.assertThat(inputDateOfVisit).isEqualTo(23);
    }

    @Test
    @DisplayName("메뉴가 입력 형식을 지키지 않으면 안된다.")
    void 메뉴_입력_형식을_지키지_않을_때_judgeInputMenuFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("파스타 1개");
        assertThatThrownBy(() -> inputJudgement.judgeInputMenuFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("없는 메뉴를 입력했을 때")
    void 없는_메뉴를_입력했을_때_judgeInputMenuFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("파스타-1,콜라-1");
        assertThatThrownBy(() -> inputJudgement.judgeInputMenuFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료수만 주문했을 때")
    void 음료수만_주문했을_때_judgeInputMenuFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("샴페인-19");
        assertThatThrownBy(() -> inputJudgement.judgeInputMenuFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("최대 주문 수량을 넘겼을 때")
    void 최대_주문_수량을_넘겼을_때_judgeInputMenuFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("샴페인-1,티본스테이크-20");
        assertThatThrownBy(() -> inputJudgement.judgeInputMenuFormatIsValid(dateOfVisit))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("메뉴 입력하기")
    void judgeInputMenuFormatIsValid() {
        Optional<String> dateOfVisit = Optional.ofNullable("샴페인-1,티본스테이크-19");
        List<MenuDto> menuDtos = inputJudgement.judgeInputMenuFormatIsValid(dateOfVisit);

        Assertions.assertThat(menuDtos.size()).isEqualTo(2);
        Assertions.assertThat(menuDtos.get(0).getMenu().toString()).isEqualTo(Menu.샴페인.toString());
        Assertions.assertThat(menuDtos.get(0).getAmount()).isEqualTo(1);
        Assertions.assertThat(menuDtos.get(1).getMenu().toString()).isEqualTo(Menu.티본스테이크.toString());
        Assertions.assertThat(menuDtos.get(1).getAmount()).isEqualTo(19);
    }
}