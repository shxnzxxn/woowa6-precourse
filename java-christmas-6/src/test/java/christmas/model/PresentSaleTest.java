package christmas.model;

import christmas.DTO.MenuDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PresentSaleTest {

    @Test
    @DisplayName("금액이 증정 기준 가격 이하면 해당 없음")
    void 금액이_증정_기준_이하일_때_getPresentMenu() {
        MenuDto presentMenu = PresentSale.getPresentMenu(0);

        Assertions.assertThat(presentMenu.getMenu()).isEqualTo(Menu.해당없음);
    }

    @Test
    @DisplayName("금액이 증정 기준 가격 이상이면 증정상품 1개")
    void 금액이_증정_기준_이상일_때_getPresentMenu() {
        MenuDto presentMenu = PresentSale.getPresentMenu(130000);

        Assertions.assertThat(presentMenu.getMenu()).isEqualTo(PresentSale.PRESENT_MENU);
        Assertions.assertThat(presentMenu.getAmount()).isEqualTo(PresentSale.PRESENT_AMOUNT);
    }

    @Test
    @DisplayName("구매 금액이 증정 기준 이하이면 0을 반환")
    void 구매_금액이_증정_기준_이하일_때_getSalePrice() {
        Integer salePrice = PresentSale.getSalePrice(20000);
        Assertions.assertThat(salePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("구매 금액이 증정 기준 이상이면 증정 상품과 증정 개수를 곱한 결과를 반환")
    void 구매_금액이_증정_기준_이상일_때_getSalePrice() {
        Integer salePrice = PresentSale.getSalePrice(290000);
        Integer presentPrice = -1*PresentSale.PRESENT_MENU.getPrice() * PresentSale.PRESENT_AMOUNT;
        Assertions.assertThat(salePrice).isEqualTo(presentPrice);
    }
}