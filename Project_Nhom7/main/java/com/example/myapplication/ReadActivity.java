package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    int comicId, chapId, positionImg;
    ListView listView;
    MyAppDatabase database;
    ArrayList<Integer> arrImg;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        setControl();
        getBundle();
        setListView();
        getHistory();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setControl(){
        database = new MyAppDatabase(this);
        listView = (ListView) findViewById(R.id.read_listview);
        bundle= getIntent().getBundleExtra("package");
        arrImg = bundle.getIntegerArrayList("data");

    }

    public void getBundle(){
        comicId = bundle.getInt("comic_id");
        chapId = bundle.getInt("chap_id");
    }

    public void setListView(){
        listView.setAdapter( new ReadListViewAdapter(this, arrImg));
    }

    public void getHistory(){

        final History history = database.getHistory(comicId);
        if(history != null && history.getChapId() == chapId) {
            listView.setSelection(history.getPositionImg());
        }
    }


    @Override
    protected void onDestroy() {
        positionImg = listView.getFirstVisiblePosition();

        History history = new History(comicId, chapId, positionImg);
        if(database.getHistory(comicId) == null)
            database.addHistory(history);
        else
            database.updateHistory(history);

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        positionImg = listView.getFirstVisiblePosition();

        History history = new History(comicId, chapId, positionImg);
        if(database.getHistory(comicId) == null)
            database.addHistory(history);
        else
            database.updateHistory(history);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
        finish();
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


}
