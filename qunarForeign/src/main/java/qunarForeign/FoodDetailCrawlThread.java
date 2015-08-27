package qunarForeign;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.DBBean;
import util.URLParser;

/**
 * Author: zxc12788.
 * Date: 2015/8/18.
 * Description:
 */
public class FoodDetailCrawlThread implements Runnable {
    private static final String category="美食";
    private String countryName;
    private String cityName;
    private String foodNameCn;
    private String foodUrl;
    private String foodCate;

    public FoodDetailCrawlThread(String countryName, String cityName, String foodNameCn, String foodUrl, String foodCate) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.foodNameCn = foodNameCn;
        this.foodUrl = foodUrl;
        this.foodCate = foodCate;
    }
    public static void insertIntoDB(POIFoodInfo poiFoodInfo){
        DBBean dbBean=null;
    }
    @Override
    public void run() {
        mainTask();
    }
    public void mainTask(){
        String foodDetailResult= URLParser.getContent(foodUrl);
        if(foodDetailResult!=null&&!foodDetailResult.equals("")){
            Document detailDoc= Jsoup.parse(foodDetailResult);
            POIFoodInfo poiFoodInfo=new POIFoodInfo();
            poiFoodInfo.setCountryName(countryName);
            poiFoodInfo.setCityName(cityName);
            poiFoodInfo.setFoodNameCn(foodNameCn);
            poiFoodInfo.setFoodUrl(foodUrl);
            Element el1=detailDoc.select("span.entit").first();
            if(!el1.equals("")&&el1!=null) {
                String foodNameEn =el1.text();
                poiFoodInfo.setFoodNameEn(foodNameEn);
            }
            String foodId=foodUrl.replace("http://travel.qunar.com/","");
            poiFoodInfo.setFoodId(foodId);
            Element latlng=detailDoc.select("div.mapbox").first();
            if(latlng!=null&&!latlng.equals("")) {
                poiFoodInfo.setLat(latlng.attr("latlng").split(",")[0]);
                poiFoodInfo.setLng(latlng.attr("latlng").split(",")[1]);
            }
            poiFoodInfo.setCategory(category);
            Elements els=detailDoc.select("div.e_summary_list.clrfix").select("dl");
            if(els!=null&&!els.equals("")) {
                for (Element el : els) {
                    String ss = el.select("dt").text().trim();
                    if (ss.equals("地址:")) {
                        poiFoodInfo.setAddress(el.select("dd>span").text());
                    } else if (ss.equals("电话:")) {
                        poiFoodInfo.setFoodTel(el.select("dd>span").text());
                    } else if (ss.equals("营业时间:")) {
                        poiFoodInfo.setOpenTime(el.select("dd>span").text());
                    }
                }
            }
            poiFoodInfo.setFoodPoint(detailDoc.select("span.cur_score").first().text());
            if(detailDoc.select("div.time").first()!=null&&!detailDoc.select("div.time").first().equals("")) {
                poiFoodInfo.setAvgPrice(detailDoc.select("div.time").first().text());
            }

            Element summary=detailDoc.select("div.b_detail_section.b_detail_summary>div.e_db_content_box>p").first();
            if (summary!=null&&!summary.equals("")) {
                poiFoodInfo.setSummaryIntro(summary.text());
            }
            Element recommend=detailDoc.select("div.b_detail_section.b_detail_travelseason>div.e_db_content_box.e_db_content_dont_indent").first();
            if(recommend!=null&&!recommend.equals("")){
                poiFoodInfo.setRecommendFood(recommend.select("p").text());
            }
            Elements traffic =detailDoc.select("#jtzn").select("p");

            if(traffic!=null&&!traffic.equals("")){
                StringBuilder stringBuilder=new StringBuilder();
                for (Element el:traffic){
                    stringBuilder.append(el.text().trim());
                }
                poiFoodInfo.setReachWay(stringBuilder.toString());
            }
            Element commentNum=detailDoc.select("#more_cmt_href").first();
            if (commentNum!=null&&!commentNum.equals("")){
                poiFoodInfo.setCommentNum(commentNum.text());
            }
            poiFoodInfo.setFoodCate(foodCate);
            //insertIntoDB(poiFoodInfo);
            System.out.println(poiFoodInfo);
        }
    }
}
