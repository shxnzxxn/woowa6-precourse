package christmas.DTO;

import java.text.DecimalFormat;

public class SaleDto {
    private Integer christmasSalePrice;
    private Integer presentSalePrice;
    private Integer weekdaySalePrice;
    private Integer weekendSalePrice;
    private Integer specialSalePrice;

    public SaleDto(Integer christmasSalePrice, Integer presentSalePrice, Integer weekdaySalePrice, Integer weekendSalePrice, Integer specialSalePrice) {
        this.christmasSalePrice = christmasSalePrice;
        this.presentSalePrice = presentSalePrice;
        this.weekdaySalePrice = weekdaySalePrice;
        this.weekendSalePrice = weekendSalePrice;
        this.specialSalePrice = specialSalePrice;
    }

    @Override
    public String toString(){
        StringBuilder saleDetail = new StringBuilder();
        saleDetail.append(getChristmasSaleDetail());
        saleDetail.append(getPresentSaleDetail());
        saleDetail.append(getWeekdaySaleDetail());
        saleDetail.append(getWeekendSaleDetail());
        saleDetail.append(getSpecialSaleDetail());

        if(saleDetail.isEmpty()){
            return "없음";
        }

        return saleDetail.toString();
    }

    private String getChristmasSaleDetail() {
        if(christmasSalePrice != 0){
            return "크리스마스 디데이 할인: "+putDividingPoint(christmasSalePrice)+"원\n";
        }
        return "";
    }

    private String getPresentSaleDetail(){
        if(presentSalePrice != 0){
            return "증정 이벤트: "+putDividingPoint(presentSalePrice) +"원\n";
        }

        return "";
    }

    private String getWeekdaySaleDetail(){
        if(weekdaySalePrice != 0){
            return "평일 할인: "+putDividingPoint(weekdaySalePrice)+"원\n";
        }

        return "";
    }

    private String getWeekendSaleDetail(){
        if(weekendSalePrice != 0){
            return "주말 할인: "+putDividingPoint(weekendSalePrice)+"원\n";
        }

        return "";
    }

    private String getSpecialSaleDetail(){
        if(specialSalePrice != 0){
            return "특별 할인: "+putDividingPoint(specialSalePrice)+"원\n";
        }

        return "";
    }

    /**
     * 증정 상품 금액을 포함한 할인 금액 계산
     * @return 증정 상품 금액을 포함한 할인 금액
     */
    public Integer getWholeSalePrice(){
        return christmasSalePrice + presentSalePrice + weekdaySalePrice + weekendSalePrice + specialSalePrice;
    }

    /**
     * 증정 상품을 제외한 할인 금액 계산
     * @return 증정 상품을 제외한 할인 금액
     */
    public Integer getSalePriceWithoutPresent() {
        return christmasSalePrice + weekdaySalePrice + weekendSalePrice + specialSalePrice;
    }

    /**
     * 구분점 찍기
     * @param price 금액
     * @return 구분점이 포함된 금액
     */
    private String putDividingPoint(Integer price){
        DecimalFormat decFormat = new DecimalFormat("###,###");
        return decFormat.format(price);
    }
}
