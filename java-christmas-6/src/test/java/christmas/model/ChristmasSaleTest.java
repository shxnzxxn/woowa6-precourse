package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ChristmasSaleTest {
    @Test
    @DisplayName("크리스마스 이벤트 대상 날짜가 아닐 때는 할인 가격이 0이다")
    void 이벤트_기간이_아닐_때_getChristmasSalePrice() {
        LocalDate date = LocalDate.of(2023, 12, 30);
        Integer christmasSalePrice = ChristmasSale.getChristmasSalePrice(200000, date);

        Assertions.assertThat(christmasSalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("이벤트 최소 주문 금액을 달성하지 못할 때는 할인 가격이 0이다")
    void 최소_주문_금액이_기준_미달일_때_getChristmasSalePrice() {
        LocalDate date = LocalDate.of(2023, 12, 24);
        Integer christmasSalePrice = ChristmasSale.getChristmasSalePrice(1000, date);

        Assertions.assertThat(christmasSalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("크리스마스 할인 금액 계산")
    void 크리스마스_할인_금액_계산_getChristmasSalePrice() {
        LocalDate date = LocalDate.of(2023,12, 16);
        Integer christmasSalePrice = ChristmasSale.getChristmasSalePrice(200000, date);

        Assertions.assertThat(christmasSalePrice).isEqualTo(-2500);
    }
}