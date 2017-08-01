package com.oven.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oven.adapter.StoriesAdapter;
import com.oven.bean.Latest;
import com.oven.bean.Stories;
import com.oven.daily_oven.R;
import com.oven.util.BaseActivity;
import com.oven.util.MyApplication;
import com.oven.util.HttpUtil;
import com.oven.util.JSONUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class LatestActivity extends BaseActivity {


    private SwipeRefreshLayout swipeRefresh;
    private DrawerLayout mDrawerLayout;
    private List<Stories> storiesList = new ArrayList<>();
    private StoriesAdapter storiesAdapter;
    private NewsHandler handler;

    private static class NewsHandler extends Handler {
        private WeakReference<LatestActivity> mReference;

        private NewsHandler(LatestActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        public void handleMessage(Message msg) {
            LatestActivity lastActivity = mReference.get();
            switch (msg.what) {
                case 1:
                    lastActivity.storiesAdapter.notifyDataSetChanged();
                    lastActivity.swipeRefresh.setRefreshing(false);

            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);

        handler = new NewsHandler(this);
        storiesAdapter = new StoriesAdapter(MyApplication.getContext(),
                R.layout.activity_latestitem, storiesList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        ListView listview = (ListView) findViewById(R.id.latest_lv_newslist);
        listview.setAdapter(storiesAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stories stories = storiesList.get(position);
                Intent intent = new Intent(LatestActivity.this, NewsActivity.class);
                intent.putExtra("newsID", stories.getStyId());
                startActivity(intent);
            }
        });
      //TODO:自动刷新或缓存
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNews();
            }
        });
    }

    private void refreshNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = null;
                try {
                    response = HttpUtil.getNewslistLatest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Latest latest = JSONUtil.handleLastResponse(response);
                showNews(latest);
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);

            }
        }).start();
    }


    public void showNews(Latest latest) {
        for (Stories story : latest.stories) {
            final Stories mStory = new Stories();
            mStory.setTitle(story.getTitle());
            mStory.setImagebitmap(HttpUtil.getImageBitmap(story.images[0]));
            mStory.setStyId(story.getStyId());
            storiesList.add(mStory);

        }
    }


    /**
     * 右上角菜单(未完成)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.setting_item:
                //TODO:settings page
                break;
            default:
        }
        return true;
    }
}





