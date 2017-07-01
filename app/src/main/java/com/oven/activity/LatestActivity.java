package com.oven.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oven.adapter.StoriesAdapter;
import com.oven.bean.Latest;
import com.oven.bean.Stories;
import com.oven.daily_oven.R;
import com.oven.util.BaseActivity;
import com.oven.util.ContextProvider;
import com.oven.util.HttpUtil;
import com.oven.util.JSONUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import static android.R.attr.handle;
import static android.R.attr.id;

public class LatestActivity extends BaseActivity {
    private SwipeRefreshLayout swipeRefresh;
    private DrawerLayout mDrawerlayout;
    private List<Stories> storiesList = new ArrayList<>();
    private StoriesAdapter storiesAdapter = new StoriesAdapter(ContextProvider.getContext(),
            R.layout.activity_latestitem,storiesList);
    private android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    storiesAdapter.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);
        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        ListView listview = (ListView) findViewById(R.id.latest_lv_newslist);
        listview.setAdapter(storiesAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stories stories = storiesList.get(position);
                Intent intent = new Intent(LatestActivity.this,NewsActivity.class);
                intent.putExtra("newsID",stories.getStyId());
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
        private void refreshNews(){
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
                    Message msg =new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } }).start();
        }




    public void showNews(Latest latest) {
        for (Stories story :latest.stories) {
            //子控件加上文字图片
            final Stories mstory = new Stories();
            mstory.setTitle(story.getTitle());
            mstory.setImagebitmap(HttpUtil.getImageBitmap(story.images[0]));
            mstory.setStyId(story.getStyId());
            storiesList.add(mstory);

        }
    }




/**
 * 右上角菜单(未完成)
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){

           case android.R.id.home:
               mDrawerlayout.openDrawer(GravityCompat.START);
               break;

           case R.id.setting_item:
               //TODO:settings page
               break;
           default:
       }
        return true;
    }
}





