package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawer;
    ArrayList<Comic> arr;
    GridView gridView;
    ListAdapter adapter=null;
    EditText search;
    MyAppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }
    private void setControl(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        gridView = (GridView) findViewById(R.id.girdview1);
        search = (EditText) findViewById(R.id.search);
    }

    private void setAdapter() {
        gridView = (GridView) findViewById(R.id.girdview1);
        adapter = new ComicGridViewAdapter(this, arr);
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
    private void setEvent()
    {
        setNavigation();

        createData();
        setAdapter();
    }

    private void createData() {
        arr = new ArrayList<>();
        //create default data
        db = new MyAppDatabase(this);
        db.createDefaultComic();
        db.createDefaultChuong();
        db.createDefaultCategory();
        db.createDefaultCategoryComic();

        arr = db.getAllComics();

    }

    private void setNavigation(){
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", search.getText().toString());
                    intent.putExtra("package",bundle);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.favorite) {
            Intent intent = new Intent(MainActivity.this, BookMarkActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            return true;
        }


//
      return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        Intent intent;
        switch (id){
            case R.id.nav_theloai:
                intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_theodoi:
                intent = new Intent(MainActivity.this, BookMarkActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_gioithieu:
                intent = new Intent(MainActivity.this, IntroduceActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_lienhe:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
