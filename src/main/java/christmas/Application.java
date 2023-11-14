package christmas;

import christmas.DTO.MenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ChristmasController christmasController = new ChristmasController(new InputView(), new OutputView(), new InputJudgement());
        LocalDate dateOfVisit = christmasController.inputDateOfVisit();
        List<MenuDto> menuToOrder = christmasController.inputMenuToOrder();
        christmasController.calculateSaleDetail(dateOfVisit, menuToOrder);
    }
}
