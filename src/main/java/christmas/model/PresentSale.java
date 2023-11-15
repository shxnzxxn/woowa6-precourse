package christmas.model;

import christmas.DTO.MenuDto;

public class PresentSale{
    private static final Integer SALE_PRICE = Menu.샴페인.getPrice();
    static final Menu PRESENT_MENU = Menu.샴페인; // 테스트를 위해 default
    private static final Integer CRITERIA_PRICE = 120000;
    static final Integer PRESENT_AMOUNT = 1;

    public static MenuDto getPresentMenu(Integer wholePrice) {
        if(wholePrice >= CRITERIA_PRICE){
            return new MenuDto(PRESENT_MENU, PRESENT_AMOUNT);
        }
        return new MenuDto(Menu.해당없음, 0);
    }

    public static Integer getSalePrice(Integer wholePrice){
        if(wholePrice >= CRITERIA_PRICE){
            return -1* SALE_PRICE;
        }

        return 0;
    }
}
