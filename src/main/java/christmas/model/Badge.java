package christmas.model;

public enum Badge {
    산타(20000), 트리(10000), 별(5000), 없음(0);

    private final Integer price;

    Badge(Integer price) {
        this.price = price;
    }

    /**
     * 전체 금액에 대해 알맞는 배지 반환
     * @param price 할인 전 금액
     * @return 금액에 따른 배지
     */
    public static Badge getBadge(Integer price){
        for (Badge badge : Badge.values()) {
            if(badge.price <= price){
                return badge;
            }
        }

        return Badge.없음;
    }
}
