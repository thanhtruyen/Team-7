package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchCategoryActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<Comic> arr;
    Category category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_category);
        Intent intent = getIntent();
        Bundle bundle= intent.getBundleExtra("package");
        category = (Category) bundle.getSerializable("category");

        setTitle(category.getTenTheLoai());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getData();
        setAdapter();

    }

    private void getData(){
        MyAppDatabase database = new MyAppDatabase(this);
        arr = new ArrayList<>();
        ArrayList<Integer> comicIdList = database.getComicIdListByCategoryId(category.getId());
        for (int comicId : comicIdList){
            arr.add(database.getComic(comicId));
        }
    }

    private void setAdapter() {
        gridView = (GridView) findViewById(R.id.gridview_search_category);
        ComicGridViewAdapter adapter = new ComicGridViewAdapter(this, arr);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ComicDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("comic",  arr.get(position));
                intent.putExtra("package",bundle);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }

}
