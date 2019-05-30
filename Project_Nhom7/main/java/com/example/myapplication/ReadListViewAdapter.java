package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReadListViewAdapter extends BaseAdapter {
    private List<Integer> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ReadListViewAdapter( Context context, List<Integer> listData) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.read_listview_item, null);
            holder = new Holder();
            holder.img = (ImageView) convertView.findViewById(R.id.read_item);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.img.setImageResource(this.listData.get(position));

        return convertView;
    }

    static class Holder {
        ImageView img;
    }
}


