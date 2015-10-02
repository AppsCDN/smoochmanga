package kishido.smoochmanga.site;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import kishido.smoochmanga.model.HotMangaInfo;
import kishido.smoochmanga.model.LatestMangaInfo;
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
        Document doc = Jsoup.parse(response);

        // Manga Suggestion
        Elements e = doc.body().getElementsByClass("details");
        Element details = e.first();

        String imageUrl = details.getElementsByTag("img").first().attr("src");
        String name = details.getElementsByClass("bigChar").first().text();
        String desc = details.getElementsByTag("p").last().text();

        MangaInfo info = new MangaInfo();
        info.setName(name);
        info.setDescription(desc);
        info.setImageUrl(imageUrl);

        Elements genres = details.getElementsByTag("p").first().getElementsByTag("a");
        for (int i=0; i<genres.size(); i++) {
            Element element = genres.get(i);
            info.addGenre(element.text());
        }

        listener.onLoadMangaSuggestion(info);

        // Latest Manga Update + Hot Updates
        e = doc.body().getElementsByClass("rightBox");

        Element hot = null, latest = null;
        for (int i=0; i<e.size(); i++) {
            Element temp = e.get(i);

            String title = temp.getElementsByClass("barTitle").text();

            if (title.contains("Latest")) {
                latest = temp;
            } else if (title.contains("Hot")) {
                hot = temp;
            }
        }

        // Hot Updates
        Elements hotUpdates = hot.getElementsByTag("strong");
        List<HotMangaInfo> hotUpdateList = new ArrayList<HotMangaInfo>();
        for (int i=0; i<hotUpdates.size(); i++) {
            Element a = hotUpdates.get(i).getElementsByTag("a").first();

            HotMangaInfo hmi = new HotMangaInfo();

            hmi.setName(a.text());
            hmi.setChapterUrl(KMRoute.BASE_URL + a.attr("href").replace("../", ""));

            hotUpdateList.add(hmi);
        }

        listener.onLoadHotUpdates(hotUpdateList);

        // Latest Updates
        Elements latestUpdates = latest.getElementsByTag("a");
        List<LatestMangaInfo> latestUpdateList = new ArrayList<LatestMangaInfo>();
        for (int i=0; i+1<latestUpdateList.size(); i+=2) {
            Element title = latestUpdates.get(i);
            Element chapter = latestUpdates.get(i+1);

            LatestMangaInfo lmi = new LatestMangaInfo();

            lmi.setName(title.text());
            lmi.setLatestChapter(chapter.text());
            lmi.setLatestChapterUrl(KMRoute.BASE_URL + chapter.attr("href").substring(1));

            latestUpdateList.add(lmi);
        }

        listener.onLoadLatestUpdates(latestUpdateList);
    }
}
