package christmas.model;

import christmas.DTO.MenuDto;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.time.DayOfWeek.*;

public class WeekdaySale {
    private static final List<DayOfWeek> WEEKEND_CRITERIA;
    private static final String MENU_TYPE_CRITERIA = "Dessert";
    private static final Integer SALE_PRICE = 2023;
    private static final Integer MINIMUM_ORDER_PRICE = 10000;

    static {
        WEEKEND_CRITERIA = new ArrayList<>();
        WEEKEND_CRITERIA.addAll(Arrays.asList(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY));
    }

    public static Integer getWeekdaySalePrice(Integer wholePrice, LocalDate dateOfVisit, List<MenuDto> menuToOrder) {
        if(!WEEKEND_CRITERIA.contains(dateOfVisit.getDayOfWeek()) || wholePrice < MINIMUM_ORDER_PRICE){
            return 0;
        }

        return calculateWeekdaySale(menuToOrder);
    }

    private static Integer calculateWeekdaySale(List<MenuDto> menuToOrder) {
        Integer salePrice = 0;

        for (MenuDto menu : menuToOrder) {
            if(menu.getMenu().getDishType().equals(MENU_TYPE_CRITERIA)){
                salePrice += (SALE_PRICE*menu.getAmount());
            }
        }

        return -1*salePrice;
    }
}
