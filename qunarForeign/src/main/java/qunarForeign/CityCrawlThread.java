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
public class CityCrawlThread implements Runnable {
    private String countryName;
    private String countryUrl;

    public CityCrawlThread(String countryName, String countryUrl) {
        this.countryName = countryName;
        this.countryUrl = countryUrl;
    }
    final ExecutorService cityPool=Executors.newFixedThreadPool(2);
    @Override
    public void run() {
        mainTask();
    }

    public void mainTask() {
        String content = URLParser.getContent(countryUrl);
        if (null != content && !content.equals("")) {
            Document doc = Jsoup.parse(content);
            Elements els = doc.select("dl.line");
            String cityUrl = "";
            String cityName = "";
            String name;
            for (Element el : els) {
                if (el.select("dt.line_tit").select("h3").text().contains("热门城市")) {
                    Elements els2 = el.select("dd");
                    for (Element el2 : els2) {
                        cityUrl = el2.select("dd").select("a").attr("href");
                        name = el2.select("dd").select("a").text();
                        int idx = name.indexOf("旅游");
                        if (idx > 0) {
                            cityName = name.substring(0, name.indexOf("旅游"));
                        } else {
                            cityName = name;
                        }
                        //System.out.println(countryName+"   "+cityName+"  "+cityUrl);
                        FoodListCrawlThread foodListCrawlThread=new FoodListCrawlThread(countryName,cityName,cityUrl);
                        cityPool.execute(foodListCrawlThread);
                    }
                }

            }
        }
        cityPool.shutdown();
    }
}
