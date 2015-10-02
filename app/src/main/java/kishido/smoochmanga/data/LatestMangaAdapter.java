package kishido.smoochmanga.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kishido.smoochmanga.R;
import kishido.smoochmanga.model.LatestMangaInfo;

/**
 * Created by syspaulo on 10/2/2015.
 */
public class LatestMangaAdapter extends BaseAdapter {

    private Context mContext;
    private List<LatestMangaInfo> latestMangaInfoList;

    public LatestMangaAdapter(Context context) {
        this.mContext = context;
        this.latestMangaInfoList = new ArrayList<LatestMangaInfo>();
    }

    @Override
    public int getCount() {
        return latestMangaInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return latestMangaInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_latest_manga, null);

            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.chapter = (TextView) view.findViewById(R.id.chapter);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        LatestMangaInfo lmi = (LatestMangaInfo) getItem(i);

        holder.title.setText(lmi.getName());
        holder.chapter.setText(lmi.getLatestChapter());

        return view;
    }

    public void add(List<LatestMangaInfo> infoList) {
        latestMangaInfoList.addAll(infoList);
    }

    static class ViewHolder {

        TextView title, chapter;
    }
}
