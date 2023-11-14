package christmas;

import christmas.DTO.MenuDto;
import christmas.DTO.SaleDto;
import christmas.model.*;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputJudgement inputJudgement;
    private final Integer EVENT_YEAR = 2023;
    private final Integer EVENT_MONTH = 12;

    public ChristmasController(InputView inputView, OutputView outputView, InputJudgement inputJudgement) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputJudgement = inputJudgement;
    }

    public LocalDate inputDateOfVisit() {
        outputView.showGreeting(EVENT_MONTH);
        Optional<String> inputDateOfVisitBeforeJudgement = inputView.readVisitDate();
        Integer dateOfVisit;
        try{
            dateOfVisit = inputJudgement.judgeInputDateOfVisitFormatIsValid(inputDateOfVisitBeforeJudgement);
        }catch(IllegalArgumentException e){
            System.out.println("[ERROR] "+e.getMessage());
            return inputDateOfVisit();
        }

        return LocalDate.of(EVENT_YEAR, EVENT_MONTH, dateOfVisit);
    }

    public List<MenuDto>  inputMenuToOrder() {
        Optional<String> inputMenuToOrderBeforeJudgement = inputView.readOrderMenu();
        List<MenuDto> inputMenu;
        try {
            inputMenu = inputJudgement.judgeInputMenuFormatIsValid(inputMenuToOrderBeforeJudgement);
        }catch(IllegalArgumentException e){
            System.out.println("[ERROR] "+e.getMessage());
            return inputMenuToOrder();
        }

        return inputMenu;
    }

    public void calculateSaleDetail(LocalDate dateOfVisit, List<MenuDto> menuToOrder) {
        outputView.showStartDetail(dateOfVisit);
        outputView.showOrderMenus(menuToOrder);

        Integer wholePrice = countWholePrice(menuToOrder);
        outputView.showPriceBeforeSale(wholePrice);

        MenuDto presentMenu = PresentSale.getMenu(wholePrice);
        outputView.showPresentMenu(presentMenu);

        SaleDto saleDto = calculateSalePrice(wholePrice, dateOfVisit, menuToOrder);
        outputView.showBenefits(saleDto);

        Integer benefitPrice = saleDto.getWholeSalePrice();
        outputView.showBenefitPrice(benefitPrice);

        Integer priceAfterSale = wholePrice + saleDto.getSalePriceWithoutPresent();
        outputView.showPriceAfterSale(priceAfterSale);

        Badge badge = Badge.getBadge(Math.abs(benefitPrice));
        outputView.showEventBadge(dateOfVisit, badge);
    }

    private SaleDto calculateSalePrice(Integer wholePrice, LocalDate dateOfVisit, List<MenuDto> menuToOrder) {
        Integer christmasSalePrice = ChristmasSale.getChristmasSalePrice(wholePrice, dateOfVisit);
        Integer salePrice = PresentSale.getSalePrice(wholePrice);
        Integer weekdaySalePrice = WeekdaySale.getWeekdaySalePrice(wholePrice, dateOfVisit, menuToOrder);
        Integer weekendSalePrice = WeekendSale.getWeekendSalePrice(wholePrice, dateOfVisit, menuToOrder);
        Integer specialSalePrice = SpecialSale.getSpecialSalePrice(wholePrice, dateOfVisit);

        return new SaleDto(christmasSalePrice, salePrice, weekdaySalePrice, weekendSalePrice, specialSalePrice);
    }

    private Integer countWholePrice(List<MenuDto> menuToOrder) {
        return menuToOrder.stream().mapToInt(MenuDto::getWholePrice).sum();
    }
}
