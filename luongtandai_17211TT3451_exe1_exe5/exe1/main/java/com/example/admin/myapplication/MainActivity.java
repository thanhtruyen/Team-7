package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText txtTen, txtTacGia;
    RadioButton rbtnVanHoc, rbtnTruyen, rbtnKhac;
    Button btnThem;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SachAdapter adapter;
    ArrayList<Sach> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setAdapter();
        data.add(new Sach("nguyen", "Truyện", "Nguyễn Du"));
        adapter.notifyDataSetChanged();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them();
            }
        });

    }

    public void setControl()
    {
        txtTen = findViewById(R.id.tensach);
        txtTacGia = findViewById(R.id.tentg);
        rbtnVanHoc = findViewById(R.id.rbtn_vanhoc);
        rbtnTruyen = findViewById(R.id.rbtn_truyen);
        rbtnKhac = findViewById(R.id.rbtn_khac);
        btnThem = findViewById(R.id.btnThem);
    }
    public void them()
    {
        String ten = txtTen.getText().toString();
        String tacgia = txtTacGia.getText().toString();
        String loaisach = "";
        if (rbtnVanHoc.isChecked())
            loaisach = "Văn học";
        else if (rbtnTruyen.isChecked())
            loaisach = "Truyện";
        else
            loaisach = "Khác";
        Sach s = new Sach(ten, loaisach, tacgia);
        data.add(s);
        adapter.notifyDataSetChanged();
    }

    public void setAdapter()
    {
        recyclerView = findViewById(R.id.recycleview);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SachAdapter(this, data, R.layout.list_item_layout);
        recyclerView.setAdapter(adapter);
    }
}
