package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Category> data;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_main);

        setControl();
        setEvent();

    }


    private void setControl() {
        listView = (ListView) findViewById(R.id.lstCategory);
        mToolbar = (Toolbar) findViewById(R.id.toolbarCategory);
    }

    private void setEvent() {
        KhoiTao();
        CategoryAdapter adapter = new CategoryAdapter(this, R.layout.list_category, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategoryActivity.this, SearchCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("category", data.get(position));
                intent.putExtra("package",bundle);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                onBackPressed();
                overridePendingTransition(0,0);

            }
        });

    }

    void KhoiTao() {
        MyAppDatabase database = new MyAppDatabase(this);
        data = database.getAllCategory();
    }


}
