package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class MucLucListViewAdapter extends ArrayAdapter<Chuong> {
    Context context;
    int resource;
    List<Chuong> arr;

    public MucLucListViewAdapter(Context context, int resource, List<Chuong> arr) {
        super(context, resource, arr);
        this.context=context;
        this.resource=resource;
        this.arr=arr;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View row = inflater.inflate(this.resource,null);

        ChapHolder holder;

        if (convertView == null) {
            holder = new ChapHolder();
            holder.chap = (TextView) row.findViewById(R.id.listview_item);
            row.setTag(holder);
        } else {
            holder = (ChapHolder) convertView.getTag();
        }
        String tenTruyen = arr.get(position).getTenChuong();
        holder.chap.setText(tenTruyen);


        return row;
    }
    static class ChapHolder {
        TextView chap;
    }
}


