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
public class CountryCrawlMain {

    final ExecutorService pool = Executors.newFixedThreadPool(3);

    public void cityUrlCrawler() {
        String startUrl = "http://travel.qunar.com/place/";
        String content = URLParser.getContent(startUrl);
        if (null != content && !content.equals("")) {
            Document doc = Jsoup.parse(content);
            Elements els = doc.select("div.contbox").get(23).select("dl.listbox");
            String countryUrl;
            String cityName;
            String countryName;
            for (Element el : els) {
                if (el.select("dt").text().equals("亚洲") || el.select("dt").text().equals("欧洲") || el.select("dt").text().equals("南美洲")
                        || el.select("dt").text().equals("北美洲") || el.select("dt").text().equals("大洋洲") || el.select("dt").text().equals("非洲")) {
                    Elements els2 = el.select("li.item");
                    for (Element el2 : els2) {
                        countryUrl = el2.select("a").attr("href");
                        countryName = el2.select("a").text();
                        if(countryName!="中国") {
                            CityCrawlThread ctn = new CityCrawlThread(countryName, countryUrl);
                            pool.execute(ctn);
                        }
                    }
                }
            }
        }
        pool.shutdown();
    }
    public static void main(String[] args) {
        new CountryCrawlMain().cityUrlCrawler();
    }
}
