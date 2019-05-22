package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SachAdapter extends    RecyclerView.Adapter<SachAdapter.MyViewHolder>{
    Context context;
    ArrayList<Sach> data;
    int layoutResourceId;

    public SachAdapter(Context context, ArrayList<Sach> data, int layoutResourceId) {
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutResourceId, viewGroup, false);
        return new MyViewHolder(row) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tensach.setText(data.get(i).getTensach());
        myViewHolder.tentg.setText(data.get(i).getTacgia());
        switch (data.get(i).getLoaisach())
        {
            case "Văn học":
                myViewHolder.loaisach.setImageResource(R.drawable.vanhoc);
                break;
            case "Truyện":
                myViewHolder.loaisach.setImageResource(R.drawable.truyen);
                break;
            case "Khác":
                myViewHolder.loaisach.setImageResource(R.drawable.khac);
                break;
                default:
                    myViewHolder.loaisach.setImageResource(R.mipmap.ic_launcher);
        }



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView loaisach;
        TextView tensach;
        TextView tentg;
        RadioButton rbtnVanHoc, rbtnTruyen, rbtnKhac;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            loaisach = itemView.findViewById(R.id.img);
            tensach = itemView.findViewById(R.id.tensach);
            tentg = itemView.findViewById(R.id.tentg);
        }
    }
}
