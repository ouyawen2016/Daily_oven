package com.oven.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oven.bean.Stories;
import com.oven.bean.Top_stories;
import com.oven.daily_oven.R;

import java.util.List;

/**
 * Created by 管理员账号 on 2017/5/10.
 */

public class Top_storiesAdapter extends ArrayAdapter<Top_stories> {
    private int resourceId;

    public Top_storiesAdapter(Context context, int textViewResourceId, List<Top_stories> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Top_stories topStories = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.newsImage = (ImageView) view.findViewById(R.id.latest_iv_showpic);
            viewHolder.newsName = (TextView) view.findViewById(R.id.latest_tv_showcon);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.newsImage.setImageBitmap(topStories.getTsbitmap());
        viewHolder.newsName.setText(topStories.getTopTitle());
        return view;
    }

    class ViewHolder {
        TextView newsName;
        ImageView newsImage;
    }

}