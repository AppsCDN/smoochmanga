package kishido.smoochmanga;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnIndexLoadListener {

    protected LatestMangaAdapter latestAdapter;
    protected HotMangaAdapter hotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        latestAdapter = new LatestMangaAdapter(this);
        ListView latestList = (ListView) findViewById(R.id.latestList);
        latestList.setAdapter(latestAdapter);

        hotAdapter = new HotMangaAdapter(this);
        ListView hotList = (ListView) findViewById(R.id.hotList);
        hotList.setAdapter(hotAdapter);

        WebCrawlerClient.lookIndex(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_manga_list) {

        } else if (id == R.id.nav_latest) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
