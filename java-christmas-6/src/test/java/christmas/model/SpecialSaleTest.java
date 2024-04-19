package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SpecialSaleTest {

    @Test
    @DisplayName("특별 할인 기간이 아니라면 0을 반환")
    void 특별_할인_기간이_아닐_때_getSpecialSalePrice() {
        LocalDate dateOfVisit = LocalDate.of(2023, 12, 11);
        Integer specialSalePrice = SpecialSale.getSpecialSalePrice(200000, dateOfVisit);

        Assertions.assertThat(specialSalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("이벤트 적용 최소 주문을 만족하지 못하면 0을 반환")
    void 최소_주문을_넘지_못할_때_getSpecialSalePrice() {
        LocalDate dateOfVisit = LocalDate.of(2023, 12, 10);
        Integer specialSalePrice = SpecialSale.getSpecialSalePrice(2000, dateOfVisit);

        Assertions.assertThat(specialSalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("이벤트 적용 조건을 만족하면 할인 금액을 반환")
    void 이벤트_적용_기간에_최소_주문_금액을_넘을_때_getSpecialSalePrice() {
        LocalDate dateOfVisit = LocalDate.of(2023, 12, 10);
        Integer specialSalePrice = SpecialSale.getSpecialSalePrice(200000, dateOfVisit);

        Assertions.assertThat(specialSalePrice).isEqualTo(-1*SpecialSale.SPECIAL_SALE_PRICE);
    }
}