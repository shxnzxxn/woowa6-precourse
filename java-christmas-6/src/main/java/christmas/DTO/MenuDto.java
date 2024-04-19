package christmas.DTO;

import christmas.model.Menu;

public class MenuDto {
    private final Menu menu;
    private final Integer amount;

    public MenuDto(Menu menu, Integer amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public Menu getMenu() {
        return menu;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getWholePrice(){
        return getAmount() * getMenu().getPrice();
    }

    @Override
    public String toString(){
        if(menu.getPrice() == 0){
            return "없음";
        }
        return menu + " "+amount+"개";
    }
}
