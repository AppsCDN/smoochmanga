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
import kishido.smoochmanga.model.HotMangaInfo;

/**
 * Created by syspaulo on 10/2/2015.
 */
public class HotMangaAdapter extends BaseAdapter {

    private Context mContext;
    private List<HotMangaInfo> hotMangaInfoList;

    public HotMangaAdapter(Context context) {
        this.mContext = context;
        this.hotMangaInfoList = new ArrayList<HotMangaInfo>();
    }

    @Override
    public int getCount() {
        return hotMangaInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return hotMangaInfoList.get(i);
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

        HotMangaInfo hmi = (HotMangaInfo) getItem(i);

        holder.title.setText(hmi.getName());
        holder.chapter.setVisibility(View.GONE);

        return view;
    }

    public void add(List<HotMangaInfo> infoList) {
        hotMangaInfoList.addAll(infoList);
    }

    static class ViewHolder {

        TextView title, chapter;
    }
}
