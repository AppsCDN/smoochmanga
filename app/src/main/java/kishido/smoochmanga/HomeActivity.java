package kishido.smoochmanga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kishido.smoochmanga.data.HotMangaAdapter;
import kishido.smoochmanga.data.LatestMangaAdapter;
import kishido.smoochmanga.model.HotMangaInfo;
import kishido.smoochmanga.model.LatestMangaInfo;
import kishido.smoochmanga.model.MangaInfo;
import kishido.smoochmanga.site.WebCrawlerClient;
import kishido.smoochmanga.site.listener.OnIndexLoadListener;

public class HomeActivity extends AppCompatActivity implements OnIndexLoadListener {

    protected LatestMangaAdapter latestAdapter;
    protected HotMangaAdapter hotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        latestAdapter = new LatestMangaAdapter(this);
        ListView latestList = (ListView) findViewById(R.id.latestList);
        latestList.setAdapter(latestAdapter);

        hotAdapter = new HotMangaAdapter(this);
        ListView hotList = (ListView) findViewById(R.id.hotList);
        hotList.setAdapter(hotAdapter);

        WebCrawlerClient.lookIndex(this);
    }

    @Override
    public void onLoadMangaSuggestion(MangaInfo info) {
        TextView name = (TextView) findViewById(R.id.name);
        TextView desc = (TextView) findViewById(R.id.desc);
        ImageView image = (ImageView) findViewById(R.id.imageManga);

        name.setText(info.getName());
        desc.setText(info.getDescription());

        Glide.with(this)
                .load(info.getImageUrl())
                .override(200, 300)
                .into(image);
    }

    @Override
    public void onLoadLatestUpdates(List<LatestMangaInfo> infoList) {
        latestAdapter.add(infoList);
        latestAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadHotUpdates(List<HotMangaInfo> infoList) {
        hotAdapter.add(infoList);
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadError(Exception e) {

    }
}
