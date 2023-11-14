package christmas.model;

public enum Badge {
    산타(20000), 트리(10000), 별(5000), 없음(0);

    private final Integer price;

    Badge(Integer price) {
        this.price = price;
    }

    public static Badge getBadge(Integer price){
        for (Badge badge : Badge.values()) {
            if(badge.price <= price){
                return badge;
            }
        }

        return Badge.없음;
    }
}
