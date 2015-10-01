package kishido.smoochmanga.model;

import java.io.Serializable;

/**
 * Created by syspaulo on 10/1/2015.
 */
public class LatestMangaInfo implements Serializable {

    private String name;
    private String latestChapter;
    private String latestChapterUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatestChapter() {
        return latestChapter;
    }

    public void setLatestChapter(String latestChapter) {
        this.latestChapter = latestChapter;
    }

    public String getLatestChapterUrl() {
        return latestChapterUrl;
    }

    public void setLatestChapterUrl(String latestChapterUrl) {
        this.latestChapterUrl = latestChapterUrl;
    }
}
