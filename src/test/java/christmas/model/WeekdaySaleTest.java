package christmas.model;

import christmas.DTO.MenuDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeekdaySaleTest {
    private List<MenuDto> notContainDessert;
    private List<MenuDto> containDessert;
    private LocalDate weekend = LocalDate.of(2023, 12, 8);
    private LocalDate weekday = LocalDate.of(2023, 12, 12);

    @BeforeEach
    void setUpStreams() {
        notContainDessert = new ArrayList<>();
        notContainDessert.add(new MenuDto(Menu.레드와인, 1));
        notContainDessert.add(new MenuDto(Menu.티본스테이크, 2));

        containDessert = new ArrayList<>();
        containDessert.add(new MenuDto(Menu.레드와인, 1));
        containDessert.add(new MenuDto(Menu.티본스테이크, 2));
        containDessert.add(new MenuDto(Menu.아이스크림, 2));
    }
    @Test
    @DisplayName("평일(일-목)이 아니라면 할인 금액은 0원이다")
    void 평일이_아닐_때_getWeekdaySalePrice() {
        Integer weekdaySalePrice = WeekdaySale.getWeekdaySalePrice(100000, weekend, containDessert);
        Assertions.assertThat(weekdaySalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("전체 금액이 최소 주문 금액을 넘지 못하면 할인 금액은 0원이다")
    void 최소_주문_금액_이하일_때_getWeekdaySalePrice() {
        Integer weekdaySalePrice = WeekdaySale.getWeekdaySalePrice(1000, weekday, containDessert);
        Assertions.assertThat(weekdaySalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("메뉴에 디저트가 없다면 할인 금액은 0원이다")
    void 디저트가_없을_때_getWeekdaySalePrice() {
        Integer weekdaySalePrice = WeekdaySale.getWeekdaySalePrice(1000000, weekday, notContainDessert);
        Assertions.assertThat(weekdaySalePrice).isEqualTo(0);
    }

    @Test
    @DisplayName("조건을 모두 만족한다면 디저트 1개당 2023원 할인")
    void 디저트_1개당_2023원_할인_getWeekdaySalePrice() {
        Integer weekdaySalePrice = WeekdaySale.getWeekdaySalePrice(1000000, weekday, containDessert);
        Assertions.assertThat(weekdaySalePrice).isEqualTo(-1*2023*2);
    }

}