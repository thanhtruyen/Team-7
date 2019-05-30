package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ComicGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Comic> arr;
    private LayoutInflater layoutInflater;

    public ComicGridViewAdapter(Context context, List<Comic> arr) {
        this.context = context;
        this.arr = arr;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.arr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ComicGridViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_comics,
                    parent, false);
            holder = new ComicGridViewHolder();
            holder.biaTruyen = (ImageView) convertView.findViewById(R.id.imageTruyen);
            holder.tenTruyen = (TextView) convertView.findViewById(R.id.tvTentruyen);
            convertView.setTag(holder);
        } else {
            holder = (ComicGridViewHolder) convertView.getTag();
        }
        String tenTruyen = arr.get(position).getTenTruyen();
        int biaTruyen = arr.get(position).getBiaTruyen();
        holder.biaTruyen.setImageResource(biaTruyen);
        holder.tenTruyen.setText(tenTruyen);


        return convertView;
        }
    static class ComicGridViewHolder {
        ImageView biaTruyen;
        TextView tenTruyen;
    }
    }
