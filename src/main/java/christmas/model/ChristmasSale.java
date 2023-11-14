package christmas.model;

import java.time.LocalDate;

public class ChristmasSale {
    public static final Integer START_EVENT_DATE = 1;
    public static final Integer END_EVENT_DATE = 25;
    private static final Integer MINIMUM_ORDER_PRICE = 10000;
    public static Integer getChristmasSalePrice(Integer price, LocalDate date){
        if(date.getDayOfMonth() > END_EVENT_DATE || price < MINIMUM_ORDER_PRICE){
            return 0;
        }

        return -1*(1000 + (date.getDayOfMonth()-START_EVENT_DATE)*100);
    }
}
