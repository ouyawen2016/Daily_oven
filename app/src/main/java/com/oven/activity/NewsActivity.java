package com.oven.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oven.bean.NewsDetails;
import com.oven.daily_oven.R;
import com.oven.util.ActivityCollector;
import com.oven.util.BaseActivity;
import com.oven.util.HttpUtil;
import com.oven.util.JSONUtil;

import java.io.IOException;

public class NewsActivity extends BaseActivity {

    //private TextView mshowDetails;
    private WebView mshowDetails;
    private TextView mshowTitle ;
    private TextView mshowPicsrc ;
    private ImageView mshowPic ;
    private NewsDetails newsDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent = getIntent();
        final String styid = intent.getStringExtra("newsID");

        mshowDetails = (WebView) findViewById(R.id.news_wv_showdetails);
        mshowTitle = (TextView)findViewById(R.id.news_tv_showtil);
        mshowPicsrc = (TextView)findViewById(R.id.news_tv_showpicsrc) ;
        mshowPic = (ImageView)findViewById(R.id.news_iv_showpic) ;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String mcontext = null;
                try {
                    mcontext= HttpUtil.getNewsDetails(styid);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                newsDetails = JSONUtil.handleNewsResponse(mcontext);

                newsDetails.setBitmap(HttpUtil.getImageBitmap(newsDetails.getImage()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showNewsDetail(newsDetails);
                    }
                });
            }
        }).start();

    }


    public void showNewsDetail(NewsDetails newsDetails){
        //TODO:怎么用textview解析html
        //TODO:调整显示
        mshowDetails.loadDataWithBaseURL(null,newsDetails.getBody(),"text/html","utf-8",null);
       // mshowDetails.setText(Html.fromHtml(newsDetails.getBody(),));
        mshowTitle.setText(newsDetails.getTitle());
        mshowPic.setImageBitmap(newsDetails.getBitmap());
        mshowPicsrc.setText(newsDetails.getImagesource());
    }



/*
     **直接退出
     */

    @Override
    public void onBackPressed() {
        Toast.makeText(NewsActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
        ActivityCollector.finishAll();
    }

}
