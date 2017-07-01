package com.oven.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.oven.bean.Stories;
import com.oven.daily_oven.R;
import java.util.List;

/**
 *新闻详情适配器
 * Created by oven on 2017/5/6.
 */
public class StoriesAdapter extends ArrayAdapter<Stories>{
    private int resourceId;
    public StoriesAdapter(Context context, int textViewResourceId, List<Stories> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Stories stories = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view =LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.newsImage= (ImageView)view.findViewById(R.id.latest_iv_showpic);
            viewHolder.newsName = (TextView)view.findViewById(R.id.latest_tv_showcon);
            view.setTag(viewHolder);
        } else{
            view = convertView;
            viewHolder =(ViewHolder)view.getTag();
        }
        viewHolder.newsImage.setImageBitmap(stories.getImagebitmap());
        viewHolder.newsName.setText(stories.getTitle());
        return view;
    }
    class ViewHolder{
        TextView newsName;
        ImageView newsImage;
    }
}

