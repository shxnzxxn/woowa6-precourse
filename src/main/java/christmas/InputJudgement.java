package christmas;

import christmas.DTO.MenuDto;
import christmas.model.Menu;
import java.util.*;

public class InputJudgement {
    private static final Integer START_DATE = 1;
    private static final Integer END_DATE = 31;
    private static final Integer MAX_ORDER_AMOUNT = 20;

    /**
     * 방문 날짜 유효성 검사
     * @param dateOfVisit 사용자가 입력한 방문 날짜
     * @return 입력받은 방문 날짜
     * @throws IllegalArgumentException 방문 날짜가 유효하지 않을 때
     */
    public Integer judgeInputDateOfVisitFormatIsValid(Optional<String> dateOfVisit) throws IllegalArgumentException {
        String inputDateOfVisit = dateOfVisit.orElse("NULL");
        Integer inputDate = isTypeInteger(inputDateOfVisit);
        isInTheRange(inputDate);

        return inputDate;
    }

    /**
     * 방문 날짜가 정수인지 확인
     * @param inputDateOfVisit 사용자가 입력한 방문 날짜
     * @return 방문 날짜
     */
    private Integer isTypeInteger(String inputDateOfVisit) {
        try{
            return Integer.parseInt(inputDateOfVisit);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    /**
     * 방문 날짜가 유효한 범위에 해당하는지 확인
     * @param inputDate 사용자가 입력한 방문 날짜
     */
    private void isInTheRange(Integer inputDate) {
        if(inputDate < START_DATE || inputDate > END_DATE){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    /**
     * 사용자가 입력한 주문 메뉴의 유효성 검사
     * @param inputMenu 사용자가 입력한 주문 메뉴
     * @return 주문 메뉴 리스트
     * @throws IllegalArgumentException 주문 메뉴의 형식이 유효하지 않을 때
     */
    public List<MenuDto> judgeInputMenuFormatIsValid(Optional<String> inputMenu) throws IllegalArgumentException {
        String inputMenuToOrderOffOptional = inputMenu.orElse("NULL");
        List<String> inputMenusBeforeJudgement = splitInputByComma(inputMenuToOrderOffOptional);
        List<MenuDto> inputMenusToOrderAfterJudgement = checkEachInputMenuFormat(inputMenusBeforeJudgement);
        // 메뉴가 음료수만 있으면 안됨
        checkIfOnlyOrderedDrink(inputMenusToOrderAfterJudgement);
        checkIfExceedMaxAmount(inputMenusToOrderAfterJudgement);

        return inputMenusToOrderAfterJudgement;
    }

    /**
     * 구분점을 기준으로 split
     * @param inputMenuToOrderOffOptional 사용자가 입력한 주문 메뉴
     * @return 구분점을 기준으로 split된 입력값
     */
    private List<String> splitInputByComma(String inputMenuToOrderOffOptional) {
        return Arrays.stream(inputMenuToOrderOffOptional.split(",")).toList();
    }

    /**
     * 입력받은 메뉴와 주문 수량이 유효한지 확인
     * @param inputMenus 사용자가 입력한 값의 주문메뉴
     * @return 주문 메뉴 리스트
     * @throws IllegalArgumentException 사용자의 입력값이 유효하지 않을 때
     */
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

    /**
     * 메뉴-개수 형식으로 입력되었는지 확인
     * @param inputMenuToOrder 사용자가 입력한 주문메뉴
     * @return - 로 분리된 사용자의 입력값
     */
    private String[] checkValidMenuFormat(String inputMenuToOrder) {
        String[] menuAndCount = inputMenuToOrder.split("-");
        if(menuAndCount.length != 2){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return menuAndCount;
    }

    /**
     * 메뉴 이름이 유효한지 확인
     * @param menuName 사용자가 입력한 주문 메뉴의 이름
     * @return 주문 메뉴에 해당하는 menu
     * @throws IllegalArgumentException 입력한 주문 메뉴가 유효하지 않을 때
     */
    private Menu isValidMenuName(String menuName) throws IllegalArgumentException {
        try {
            return Menu.valueOf(menuName);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    /**
     * 사용자가 입력한 주문메뉴의 수량이 유효한 형태인지 확인
     * @param menuCount 사용자가 입력한 주문메뉴의 수량
     * @return 주문 메뉴의 수량
     */
    private Integer isValidMenuCount(String menuCount) {
        try{
            return Integer.parseInt(menuCount);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    /**
     * 사용자가 Drink만 주문하였는지 확인
     * @param inputMenusToOrderBeforeJudgement 사용자가 입력한 주문 메뉴
     */
    private void checkIfOnlyOrderedDrink(List<MenuDto> inputMenusToOrderBeforeJudgement) {

        for (MenuDto orderMenu : inputMenusToOrderBeforeJudgement) {
            if(!orderMenu.getMenu().getDishType().equals("Drink")){
                return;
            }
        }

        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    /**
     * 사용자가 입력한 주문 수량이 최대 주문 수량을 넘는지 확인
     * @param inputMenusToOrderAfterJudgement 사용자가 입력한 주문 메뉴
     */
    private void checkIfExceedMaxAmount(List<MenuDto> inputMenusToOrderAfterJudgement) {

        int wholeAmount = inputMenusToOrderAfterJudgement.stream().mapToInt(MenuDto::getAmount).sum();

        if(wholeAmount > MAX_ORDER_AMOUNT){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
