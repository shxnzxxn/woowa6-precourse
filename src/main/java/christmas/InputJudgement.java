package christmas;

import christmas.DTO.MenuDto;
import christmas.model.Menu;
import java.util.*;

public class InputJudgement {
    private static final Integer START_DATE = 1;
    private static final Integer END_DATE = 31;
    private static final Integer MAX_ORDER_AMOUNT = 20;

    public Integer judgeInputDateOfVisitFormatIsValid(Optional<String> dateOfVisit) throws IllegalArgumentException {
        String inputDateOfVisit = dateOfVisit.orElse("NULL");
        Integer inputDate = isTypeInteger(inputDateOfVisit);
        isInTheRange(inputDate);

        return inputDate;
    }

    private Integer isTypeInteger(String inputDateOfVisit) {
        try{
            return Integer.parseInt(inputDateOfVisit);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void isInTheRange(Integer inputDate) {
        if(inputDate < START_DATE || inputDate > END_DATE){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public List<MenuDto> judgeInputMenuFormatIsValid(Optional<String> inputMenu) throws IllegalArgumentException {
        String inputMenuToOrderOffOptional = inputMenu.orElse("NULL");
        List<String> inputMenusBeforeJudgement = splitInputByComma(inputMenuToOrderOffOptional);
        List<MenuDto> inputMenusToOrderAfterJudgement = checkEachInputMenuFormat(inputMenusBeforeJudgement);
        // 메뉴가 음료수만 있으면 안됨
        checkIfOnlyOrderedDrink(inputMenusToOrderAfterJudgement);
        checkIfExceedMaxAmount(inputMenusToOrderAfterJudgement);

        return inputMenusToOrderAfterJudgement;
    }

    private List<String> splitInputByComma(String inputMenuToOrderOffOptional) {
        return Arrays.stream(inputMenuToOrderOffOptional.split(",")).toList();
    }

    private List<MenuDto> checkEachInputMenuFormat(List<String> inputMenus) throws IllegalArgumentException {
        List<MenuDto> orderMenus = new ArrayList<>();
        // 메뉴-개수 형식으로 입력되어 있어야함.
        inputMenus.forEach(inputMenuToOrder -> {
            String[] menuAndCount = checkValidMenuFormat(inputMenuToOrder);
            // 메뉴 이름 검증
            Menu orderMenu = isValidMenuName(menuAndCount[0].trim());
            // 개수 검증
            Integer count = isValidMenuCount(menuAndCount[1].trim());

            MenuDto menuDto = new MenuDto(orderMenu, count);
            orderMenus.add(menuDto);
        });

        return orderMenus;
    }

    private String[] checkValidMenuFormat(String inputMenuToOrder) {
        String[] menuAndCount = inputMenuToOrder.split("-");
        if(menuAndCount.length != 2){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return menuAndCount;
    }

    private Menu isValidMenuName(String menuName) throws IllegalArgumentException {
        try {
            return Menu.valueOf(menuName);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private Integer isValidMenuCount(String menuCount) {
        try{
            return Integer.parseInt(menuCount);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void checkIfOnlyOrderedDrink(List<MenuDto> inputMenusToOrderBeforeJudgement) {

        for (MenuDto orderMenu : inputMenusToOrderBeforeJudgement) {
            if(!orderMenu.getMenu().getDishType().equals("Drink")){
                return;
            }
        }

        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    private void checkIfExceedMaxAmount(List<MenuDto> inputMenusToOrderAfterJudgement) {

        int wholeAmount = inputMenusToOrderAfterJudgement.stream().mapToInt(MenuDto::getAmount).sum();

        if(wholeAmount > MAX_ORDER_AMOUNT){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
