# View
### InputView
    - readVisitDate : 방문 날짜 입력 받기
    = readOrderMenu : 주문 메뉴 입력 받기

### OutputView
    - showGreeting : "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다." 출력
    - showStartDetail : "OO월 OO일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" 출력
    - showOrderMenus : 주문 메뉴 리스트 출력
    - showPriceBeforeSale : 할인 전 총주문 금액 출력
    - showPresentMenu : 증정 메뉴 출력
    - showBenefits : 혜택 내역 출력
    - showBenefitPrice : 총혜택 금액 출력
    - showPriceAfterSale : 할인 후 예상 결제 금액 출력
    - showEventBadge : 배지 출력

# Menu
    - 메뉴이름(가격, 메뉴타입)
        ex. 양송이스프(6000, "Appetizer"), 해당없음(0, "None")

    - getPrice : 메뉴의 가격 반환
    - getDishType : 메뉴의 타입(Appetizer, Main, Drink, Dessert) 반환

# Sale
### ChristmasSale
    getChristmasSalePrice
### PresentSale
    getPresentSalePrice
### WeekdaySale
    getWeekdaySalePrice
### WeekendSale
    getWeekendSalePrice
### SpecialSale
    getSpecialSalePrice

# Badge
    getBadge

# InputJudgement
### judgeInputDateOfVisitFormatIsValid
    :방문 날짜의 유효성 검사
      - isTypeInteger : 방문 날짜의 값이 정수인지 확인
      - isInTheRange : 방문 날짜가 유효한 범위 내에 있는지 확인

### judgeInputMenuFormatIsValid
    :주문 메뉴의 유효성 검사
      - splitInputByComma : 구분점(,)을 기준으로 분리
      - checkEachInputMenuFormat : 메뉴에 있는 값을 입력했는지 확인
      - checkIfOnlyOrderedDrink : 음료만 시켰는지 확인
      - checkIfExceedMaxAmount : 최대 주문 수량을 넘기는지 확인

# Controller
### ChristmasController 
    - inputDateOfVisit : 방문 날짜 입력 받고 유효성 검사
    - inputMenuToOrder : 주문할 메뉴 입력 받고 유효성 검사
    - calculateSaleDetail : 주문 메뉴의 할인 항목 검사
    - calculateSalePrice : 주문 메뉴의 할인 가격 검사

# DTO
### MenuDto
    - getWholePrice

### SaleDto
    - getWholeSalePrice : 전체 할인 금액 계산
    - getSalePriceWhithoutPresent : 증정 금액을 제외한 할인 금액 계산