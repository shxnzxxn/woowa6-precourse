package christmas.view;

import christmas.DTO.MenuDto;
import christmas.DTO.SaleDto;
import christmas.model.Badge;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class OutputView {
    public void showGreeting(Integer month){
        System.out.println("안녕하세요! 우테코 식당 " + month + "월 이벤트 플래너입니다.");
    }

    public void showStartDetail(LocalDate dateOfVisit){
        System.out.println(dateOfVisit.getMonthValue() + "월 " + dateOfVisit.getDayOfMonth() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void showOrderMenus(List<MenuDto> menuToOrder) {
        System.out.println("<주문 메뉴>");
        menuToOrder.forEach(System.out::println);
        System.out.println();
    }

    public void showPriceBeforeSale(Integer price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(putDividingPoint(price)+"원\n");
    }

    public void showPresentMenu(MenuDto presentMenu) {
        System.out.println("<증정 메뉴>");
        System.out.println(presentMenu+"\n");
    }

    public void showBenefits(SaleDto saleDto) {
        System.out.println("<혜택 내역>");
        System.out.println(saleDto +"\n");
    }

    public void showBenefitPrice(Integer wholePrice) {
        System.out.println("<총혜택 금액>");
        System.out.println(putDividingPoint(wholePrice)+"원\n");
    }

    public void showPriceAfterSale(Integer wholePrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(putDividingPoint(wholePrice)+"원\n");
    }

    public void showEventBadge(LocalDate dateOfVisit, Badge badge) {
        System.out.println("<"+dateOfVisit.getMonthValue()+"월 이벤트 배지>");
        System.out.println(badge);
    }

    private String putDividingPoint(Integer price){
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return decFormat.format(price);
    }
}
