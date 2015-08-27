package util;

import org.apache.http.HttpHost;

import java.util.Map;

/**
* Description Of The Class<br/>
*
* @author Quinn Wang(王中奎)
* @version 1.0.0, 2015/3/10 12:54
* @since 2015/3/10 12:54
*/
public class URLParser {
    public static String getContent(String pageUrl) {
        if (null == pageUrl) {
            return null;
        }
        return HttpUtil.getUrlContent(HttpClientManager.getCloseableHttpClient(), null, new HttpHost("", , "http"), pageUrl, null, "UTF-8");
    }

    public static String postContent(String pageUrl, Map<String, String> params){
        if (null == pageUrl) {
            return null;
        }
        return HttpUtil.postUrlContent(HttpClientManager.getCloseableHttpClient(), null, new HttpHost("", , "http"), pageUrl, params,
        null, "UTF-8");
    }



}
