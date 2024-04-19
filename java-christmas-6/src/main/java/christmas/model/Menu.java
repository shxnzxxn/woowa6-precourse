package christmas.model;

public enum Menu {
    양송이스프(6000, "Appetizer"), 타파스(5500, "Appetizer"), 시저샐러드(8000, "Appetizer"),
    티본스테이크(55000, "Main"), 바비큐립(54000, "Main"), 해산물파스타(35000, "Main"), 크리스마스파스타(25000, "Main"),
    초코케이크(15000, "Dessert"), 아이스크림(5000, "Dessert"),
    제로콜라(3000, "Drink"), 레드와인(60000, "Drink"), 샴페인(25000, "Drink"),
    해당없음(0, "None");

    private final Integer price;
    private final String dishType;

    Menu(Integer price, String dishType) {
        this.price = price;
        this.dishType = dishType;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDishType() {
        return dishType;
    }
}
