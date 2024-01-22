package christmas.model;

import christmas.DTO.MenuDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class WeekendSaleTest {
    private List<MenuDto> notContainMain;
    private List<MenuDto> containMain;
    private LocalDate weekend = LocalDate.of(2023, 12, 8);
    private LocalDate weekday = LocalDate.of(2023, 12, 12);

    @BeforeEach
    void setUpStreams() {
        notContainMain = new ArrayList<>();
        notContainMain.add(new MenuDto(Menu.아이스크림, 1));
        notContainMain.add(new MenuDto(Menu.시저샐러드, 2));

        containMain = new ArrayList<>();
        containMain.add(new MenuDto(Menu.레드와인, 1));
        containMain.add(new MenuDto(Menu.티본스테이크, 2));
        containMain.add(new MenuDto(Menu.아이스크림, 2));
    }
    @Test
    @DisplayName("주말(금, 토)이 아니라면 할인 금액은 0원이다")
    void 평일이_아닐_때_getWeekendSalePrice() {
        Integer weekendSalePrice = WeekendSale.getWeekendSalePrice(100000, weekday, containMain);
        Assertions.assertThat(weekendSalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("전체 금액이 최소 주문 금액을 넘지 못하면 할인 금액은 0원이다")
    void 최소_주문_금액_이하일_때_getWeekendSalePrice() {
        Integer weekendSalePrice = WeekendSale.getWeekendSalePrice(1000, weekend, containMain);
        Assertions.assertThat(weekendSalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("메뉴에 메인메뉴가 없다면 할인 금액은 0원이다")
    void 디저트가_없을_때_getWeekendSalePrice() {
        Integer weekendSalePrice = WeekendSale.getWeekendSalePrice(1000000, weekend, notContainMain);
        Assertions.assertThat(weekendSalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("조건을 모두 만족한다면 메인메뉴 1개당 2023원 할인")
    void 디저트_1개당_2023원_할인_getWeekendSalePrice() {
        Integer weekendSalePrice = WeekendSale.getWeekendSalePrice(1000000, weekend, containMain);
        Assertions.assertThat(weekendSalePrice).isEqualTo(-1*2023*2);
    }

}