package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ComicDetailActivity extends AppCompatActivity {
    ImageView thumbnail;
    TextView comicName, comicCategory, newestChap;
    Button btnContinue;
    ToggleButton btnSubscribe;
    TabLayout tabLayout;
    ViewPager viewPager;
    Comic comic;
    MyAppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        setControl();
        getData();

        //hien back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContent();



    }

    private void setControl(){
        thumbnail = (ImageView) findViewById(R.id.thumbnail);
        comicName = (TextView) findViewById(R.id.comicName);
        comicCategory = (TextView) findViewById(R.id.comicCategory);
        newestChap = (TextView) findViewById(R.id.newestChap);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnSubscribe = (ToggleButton) findViewById(R.id.btnSubscribe);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_page);

    }

    private void getData(){
        Intent intent = getIntent();
        Bundle bundle= intent.getBundleExtra("package");
        comic = (Comic) bundle.getSerializable("comic");

        database = new MyAppDatabase(this);

        comic.setArrChuong(database.getChuongByComicId(comic.getId()));

    }

    private void setBtnContinue() {
        btnContinue.setEnabled(false);
        final History history = database.getHistory(comic.getId());
        if(history != null) {
            btnContinue.setEnabled(true);
            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("data", database.getChuong(history.getChapId()).getNoiDungHinhAnh());
                    bundle.putInt("comic_id",  history.getComicId());
                    bundle.putInt("chap_id", history.getChapId());
                    Intent intent = new Intent(ComicDetailActivity.this, ReadActivity.class);
                    intent.putExtra("package", bundle);

                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            });
        }
    }

    private void setContent(){
        //set comicCategory
        ArrayList<Integer> categoryIdList = database.getCategoryIdListByComicId(comic.getId());
        if(categoryIdList.size() == 0){
            comicCategory.setText("Thể loại: Chưa cập nhật");
        }
        else{
            ArrayList<Category> categoryList = new ArrayList<>();
            for(int categoryId : categoryIdList){
                categoryList.add(database.getCategory(categoryId));
            }

            String strCategory = "Thể loại: ";
            for(Category category : categoryList){
                strCategory += category.getTenTheLoai() + ", ";
            }
            comicCategory.setText(strCategory);
        }

        //set Newestchap
        String strNewestChap = comic.getArrChuong().size() - 1 >= 0 ? comic.getArrChuong().get(comic.getArrChuong().size() - 1).getTenChuong() : "Chưa cập nhật";
        newestChap.setText("Chap mới: " + strNewestChap);// chap mới nhất là chap cuối cùng của mảng

        //set Title
        setTitle(comic.getTenTruyen());

        //set Thumbnail
        thumbnail.setImageResource(comic.getBiaTruyen());

        //set Name
        comicName.setText(comic.getTenTruyen());

        //ToggleButton
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSubscribe.startAnimation(animation);
            }
        });
        btnSubscribe.setChecked(database.getSubscribe(comic.getId()) == -1 ? false : true);
        btnSubscribe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    database.addSubscribe(comic.getId());
                }
                else{
                    database.deleteSubscribe(comic.getId());
                }
            }
        });

        //btnContinue
       setBtnContinue();

        //Tab layout
        tabLayout.addTab(tabLayout.newTab().setText("NỘI DUNG"));
        tabLayout.addTab(tabLayout.newTab().setText("MỤC LỤC"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final PagerAdapter adapter = new ComicDetailPagerAdapter(getSupportFragmentManager());
        ((ComicDetailPagerAdapter) adapter).setComic(comic);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setBtnContinue();
            }
        }, 500);
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
