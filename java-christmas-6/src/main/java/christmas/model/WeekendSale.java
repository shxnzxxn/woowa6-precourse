package christmas.model;

import christmas.DTO.MenuDto;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;

public class WeekendSale {
    private static final List<DayOfWeek> WEEKEND_CRITERIA;
    private static final String MENU_TYPE_CRITERIA = "Main";
    private static final Integer SALE_PRICE = 2023;
    private static final Integer MINIMUM_ORDER_PRICE = 10000;

    static {
        WEEKEND_CRITERIA = new ArrayList<>();
        WEEKEND_CRITERIA.addAll(Arrays.asList(FRIDAY, SATURDAY));
    }

    public static Integer getWeekendSalePrice(Integer wholePrice, LocalDate dateOfVisit, List<MenuDto> menuToOrder) {
        if(!WEEKEND_CRITERIA.contains(dateOfVisit.getDayOfWeek()) || wholePrice < MINIMUM_ORDER_PRICE){
            return 0;
        }

        return calculateWeekendSale(menuToOrder);
    }

    /**
     * 주말 할인 금액 계산
     * @param menuToOrder 주문 정보
     * @return 메인 메뉴에 따른 할인 금액 계산
     */
    private static Integer calculateWeekendSale(List<MenuDto> menuToOrder) {
        Integer salePrice = 0;

        for (MenuDto menu : menuToOrder) {
            if(menu.getMenu().getDishType().equals(MENU_TYPE_CRITERIA)){
                salePrice += (SALE_PRICE*menu.getAmount());
            }
        }

        return -1*salePrice;
    }
}
