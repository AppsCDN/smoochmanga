package kishido.smoochmanga.site.listener;

import java.util.List;

import kishido.smoochmanga.model.HotMangaInfo;
import kishido.smoochmanga.model.LatestMangaInfo;
import kishido.smoochmanga.model.MangaInfo;

/**
 * Created by syspaulo on 10/1/2015.
 */
public interface OnIndexLoadListener {

    void onLoadMangaSuggestion(MangaInfo info);

    void onLoadLatestUpdates(List<LatestMangaInfo> infoList);

    void onLoadHotUpdates(List<HotMangaInfo> infoList);

    void onLoadError(Exception e);
}
