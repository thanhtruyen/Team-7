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
    ArrayList<KeHoach> data;
    int layoutResource;

    public Adapter(Context context, ArrayList<KeHoach> data, int layoutResource) {
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
        myViewHolder.txtSoA.setText(data.get(i).getNgay());
        myViewHolder.txtSoB.setText(data.get(i).getTieude());
        myViewHolder.txtKQ.setText(data.get(i).getNoidung());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imKq;

        TextView txtSoA, txtSoB, txtKQ;


        public MyViewHolder(final View itemView) {
            super(itemView);
            this.txtSoA = (TextView) itemView.findViewById(R.id.vSoA);
            this.txtSoB = (TextView) itemView.findViewById(R.id.vSoB);
            this.txtKQ = (TextView) itemView.findViewById(R.id.vKq);

            this.imKq = (ImageView) itemView.findViewById(R.id.image);

        }
    }

}
