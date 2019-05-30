package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {
    Context context;
    int layoutResourceId;
    ArrayList<Category> data = null;

    public CategoryAdapter(Context context, int layoutResourceId, ArrayList<Category> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TheLoaiHolder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TheLoaiHolder();
            holder.textView = (TextView) row.findViewById(R.id.tvCategory);
            holder.img = (ImageView) row.findViewById(R.id.category_item_img);
            row.setTag(holder);
        }
        else{
            holder = (TheLoaiHolder)row.getTag();
        }
        Category item = data.get(position);
        holder.textView.setText(item.getTenTheLoai());
        holder.img.setImageResource(item.getImage());
        return row;
    }
    static class TheLoaiHolder{
        TextView textView;
        ImageView img;
    }
}
