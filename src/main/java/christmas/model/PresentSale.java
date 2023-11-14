package christmas.model;

import christmas.DTO.MenuDto;

public class PresentSale{
    private static final Integer salePrice = Menu.샴페인.getPrice();
    private static final Menu presentMenu = Menu.샴페인;
    private static final Integer criteriaPrice = 120000;

    public static MenuDto getMenu(Integer wholePrice) {
        if(wholePrice >= criteriaPrice){
            return new MenuDto(presentMenu, 1);
        }
        return new MenuDto(Menu.해당없음, 0);
    }

    public static Integer getSalePrice(Integer wholePrice){
        if(wholePrice >= criteriaPrice){
            return -1*salePrice;
        }

        return 0;
    }
}
