package kishido.smoochmanga.site;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cz.msebera.android.httpclient.Header;
import kishido.smoochmanga.model.MangaInfo;
import kishido.smoochmanga.site.listener.OnIndexLoadListener;

/**
 * Created by syspaulo on 10/1/2015.
 */
public class WebCrawlerClient {

    private static final AsyncHttpClient client = new AsyncHttpClient();

    public static void lookIndex(final OnIndexLoadListener listener) {
        client.setUserAgent("Mozilla/5.0");
        client.get(KMRoute.BASE_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onLoadError(new Exception(throwable.getMessage()));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseIndex(responseString, listener);
            }
        });
    }

    private static void parseIndex(String response, OnIndexLoadListener listener) {

    }
}
