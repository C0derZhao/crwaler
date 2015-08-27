package qunarForeign;

/**
 * Author: zxc12788.
 * Date: 2015/8/18.
 * Description:
 */
public class POIFoodInfo {
    @Override
    public String toString() {
        return "POIFoodInfo{" +
                "countryName='" + countryName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", foodNameCn='" + foodNameCn + '\'' +
                ", foodNameEn='" + foodNameEn + '\'' +
                ", foodUrl='" + foodUrl + '\'' +
                ", foodId='" + foodId + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", category='" + category + '\'' +
                ", address='" + address + '\'' +
                ", foodTel='" + foodTel + '\'' +
                ", avgPrice='" + avgPrice + '\'' +
                ", summaryIntro='" + summaryIntro + '\'' +
                ", foodPoint='" + foodPoint + '\'' +
                ", ReachWay='" + ReachWay + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", openTime='" + openTime + '\'' +
                ", foodCate='" + foodCate + '\'' +
                ", recommendFood='" + recommendFood + '\'' +
                '}';
    }

    private String countryName;
    private String cityName;
    private String foodNameCn;
    private String foodNameEn;
    private String foodUrl;
    private String foodId;
    private String lat;
    private String lng;
    private String category;
    private String address;
    private String foodTel;
    private String avgPrice;
    //简介
    private String summaryIntro;
    private String foodPoint;
    //到达方式
    private String ReachWay;
    private String commentNum;
    private String openTime;
    //美食类型
    private String foodCate;
    private String recommendFood;

    public POIFoodInfo(String countryName, String cityName, String foodNameCn, String foodNameEn, String foodUrl, String foodId, String lat, String lng, String category, String address, String foodTel, String avgPrice, String summaryIntro, String foodPoint, String reachWay, String commentNum, String openTime, String foodCate, String recommendFood) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.foodNameCn = foodNameCn;
        this.foodNameEn = foodNameEn;
        this.foodUrl = foodUrl;
        this.foodId = foodId;
        this.lat = lat;
        this.lng = lng;
        this.category = category;
        this.address = address;
        this.foodTel = foodTel;
        this.avgPrice = avgPrice;
        this.summaryIntro = summaryIntro;
        this.foodPoint = foodPoint;
        ReachWay = reachWay;
        this.commentNum = commentNum;
        this.openTime = openTime;
        this.foodCate = foodCate;
        this.recommendFood = recommendFood;
    }

    public POIFoodInfo() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFoodNameCn() {
        return foodNameCn;
    }

    public void setFoodNameCn(String foodNameCn) {
        this.foodNameCn = foodNameCn;
    }

    public String getFoodNameEn() {
        return foodNameEn;
    }

    public void setFoodNameEn(String foodNameEn) {
        this.foodNameEn = foodNameEn;
    }

    public String getFoodUrl() {
        return foodUrl;
    }

    public void setFoodUrl(String foodUrl) {
        this.foodUrl = foodUrl;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFoodTel() {
        return foodTel;
    }

    public void setFoodTel(String foodTel) {
        this.foodTel = foodTel;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getSummaryIntro() {
        return summaryIntro;
    }

    public void setSummaryIntro(String summaryIntro) {
        this.summaryIntro = summaryIntro;
    }

    public String getFoodPoint() {
        return foodPoint;
    }

    public void setFoodPoint(String foodPoint) {
        this.foodPoint = foodPoint;
    }

    public String getReachWay() {
        return ReachWay;
    }

    public void setReachWay(String reachWay) {
        ReachWay = reachWay;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getFoodCate() {
        return foodCate;
    }

    public void setFoodCate(String foodCate) {
        this.foodCate = foodCate;
    }

    public String getRecommendFood() {
        return recommendFood;
    }

    public void setRecommendFood(String recommendFood) {
        this.recommendFood = recommendFood;
    }
}