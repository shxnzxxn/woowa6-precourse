package christmas.model;

import java.time.LocalDate;
import java.util.List;

public class SpecialSale {
    private static final List<Integer> EVENT_DATE = List.of(3, 10, 17, 24, 25, 31);
    private static final Integer MINIMUM_ORDER_PRICE = 10000;

    public static Integer getSpecialSalePrice(Integer wholePrice, LocalDate dateOfVisit) {
        if(!EVENT_DATE.contains(dateOfVisit.getDayOfMonth()) || wholePrice < MINIMUM_ORDER_PRICE){
            return 0;
        }

        return -1000;
    }
}
