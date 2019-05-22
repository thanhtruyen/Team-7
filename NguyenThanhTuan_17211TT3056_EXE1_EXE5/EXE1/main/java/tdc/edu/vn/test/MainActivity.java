package tdc.edu.vn.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<Sach> data = new ArrayList<>();

    EditText nhap1 ;
    EditText nhap2 ;

    Button btnCong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        khaiBao();
        setAdapter();
        suKien();
    }
    private void setAdapter(){
        recyclerView = findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, data, R.layout.view_item);
        recyclerView.setAdapter(adapter);
    }


    private void khaiBao(){
        nhap1 = (EditText)findViewById(R.id.input1);
        nhap2 = (EditText)findViewById(R.id.input2);
        btnCong = (Button)findViewById(R.id.btnAdd);
    }
    private void suKien(){
        data.add(new Sach("Test","Test"));
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(new Sach(nhap1.getText().toString(),nhap2.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
    }
}
