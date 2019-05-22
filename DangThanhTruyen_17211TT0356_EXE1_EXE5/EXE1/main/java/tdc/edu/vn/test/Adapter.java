package tdc.edu.vn.test;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    ArrayList<Sach> data;
    int layoutResource;

    public Adapter(Context context, ArrayList<Sach> data, int layoutResource) {
        this.context = context;
        this.data = data;
        this.layoutResource = layoutResource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(layoutResource, viewGroup, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtSoA.setText(data.get(i).getSoA());
        myViewHolder.txtSoB.setText(data.get(i).getSoB());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imKq;
        TextView txtSoA, txtSoB, txtPhepTinh, txtKQ;


        public MyViewHolder(final View itemView) {
            super(itemView);
            this.txtSoA = (TextView) itemView.findViewById(R.id.vinput1);
            this.txtSoB = (TextView) itemView.findViewById(R.id.vinput2);
            this.imKq = (ImageView) itemView.findViewById(R.id.image);

        }
    }

}
