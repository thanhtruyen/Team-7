package tdc.edu.vn.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<KeHoach> data = new ArrayList<>();

    EditText ngay ;
    EditText tieude ;
    EditText noidung;
    Button btnThem;
//    RadioButton radthongbao;
//    RadioButton radkhongthongbao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        khaiBao();
        setAdapter();
        suKien();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.more:
                Toast.makeText(this, "view more", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter(){
        recyclerView = findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        if (radkhongthongbao.isChecked()) {
//            adapter = new Adapter(this,data,R.layout.view_item2);
//            recyclerView.setAdapter(adapter);
//        }
//        else
//        {
//            adapter =new Adapter(this,data,R.layout.view_item1);
//            recyclerView.setAdapter(adapter);
//        }
        adapter = new Adapter(this,data,R.layout.view_item1);
        recyclerView.setAdapter(adapter);
    }


    private void khaiBao(){
        ngay = (EditText)findViewById(R.id.inputNgay);
        tieude = (EditText)findViewById(R.id.inputTieude);
        noidung = (EditText)findViewById(R.id.inputNoidung);
        btnThem=(Button)findViewById(R.id.btnthem);

    }
    private void suKien(){
        data.add(new KeHoach("12/04/2019","dlt","8h di lam them"));
        data.add(new KeHoach("15/04/2019","sn","19h sinh nhat ban"));

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(new KeHoach(ngay.getText().toString(),tieude.getText().toString(),noidung.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
    }
}
