package qunarForeign;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.URLParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: zxc12788.
 * Date: 2015/8/18.
 * Description:
 */
public class FoodListCrawlThread implements Runnable {
//
    private String countryName;
    private String cityName;
    private String cityUrl;

    public FoodListCrawlThread(String countryName, String cityName, String cityUrl) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.cityUrl = cityUrl;
    }
    final ExecutorService foodDetailPool= Executors.newFixedThreadPool(3);
    @Override
    public void run() {
        mainTask();
    }
    public void mainTask(){
        //String cityNavi=cityUrl;
        String cityResult= URLParser.getContent(cityUrl);
        if(cityResult!=null&&!cityResult.equals("")){
            Document cityDoc= Jsoup.parse(cityResult);
            Elements els=cityDoc.select("ul.navs").first().select("li");
            boolean flag=false;
            for(Element el:els){
                if(el.select("a").text().equals("美食")){
                    flag=true;
                }
            }
            //存在美食这一栏
            if(flag){
                String foodListUrl=cityUrl+"-meishi";
                String foodListResult= URLParser.getContent(foodListUrl);
                if(foodListResult!=null&&!foodListResult.equals("")){
                    Document foodListDoc=Jsoup.parse(foodListResult);
                    Elements pageNum=foodListDoc.select("div.b_paging>a");
                    int totalPage=1;
                    if(pageNum!=null&&!pageNum.equals("")) {
                        totalPage = Integer.parseInt(pageNum.get(pageNum.size() - 2).text());
                    }
                    for(int i=1;i<=totalPage;i++){
                        String pageUrl=foodListUrl+"?page="+i;
                        String pageContent=URLParser.getContent(pageUrl);
                        if(pageContent!=null&&!pageContent.equals("")){
                            Document pageDoc=Jsoup.parse(pageContent);
                            Elements foodListEL=pageDoc.select("ul.list_item.clrfix>li");
                            for(Element element:foodListEL){
                                String foodNameCn=element.select("div.titbox.clrfix>a>span").text();
                                String foodUrl=element.select("div.titbox.clrfix>a").attr("href");
                                Elements elements=element.select("div.sublistbox>dl.sublist_item.clrfix");
                                String foodCate="";
                                for (Element el1:elements){
                                    String s=el1.select("dt.sub_tit").text().replaceAll("　","");
                                    //System.out.println(s+"  "+foodCate);
                                    if(s.equals("类型")){
                                        foodCate=el1.select("dd.sub_des").text();
                                    }
                                }
                                FoodDetailCrawlThread foodDetailCrawlThread=new FoodDetailCrawlThread(countryName,cityName,foodNameCn,foodUrl,foodCate);
                                foodDetailPool.execute(foodDetailCrawlThread);
                            }
                        }
                    }
                }
            }
        }
        foodDetailPool.shutdown();
    }
}
