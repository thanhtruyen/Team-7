package tdc.edu.vn.baitap;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle , txtstart, txtend;
    Button btnprev, btnplay, btnstop,btnnext;
    ImageView imgaev;
    SeekBar sksong;
    MediaPlayer media;
    ArrayList<song> arrsong;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setEvent();
        Add();
        Onlick();
        khoitao();

    }

    private void Onlick() {
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(media.isPlaying()){
                    media.pause();
                    btnplay.setBackgroundResource(R.drawable.play);
                }else{
                    media.start();
                    btnplay.setBackgroundResource(R.drawable.pause);
                }
                setTime();
                updateTime();
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media.stop();
                media.release();
                btnplay.setBackgroundResource(R.drawable.play);
                khoitao();
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position > arrsong.size() -1)
                {
                    position = 0;
                }
                if(media.isPlaying())
                {
                    media.stop();
                }
                khoitao();
                media.start();
                btnplay.setBackgroundResource(R.drawable.pause);
                setTime();
            }
        });
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0){
                    position = arrsong.size() -1;
                }
                if(media.isPlaying())
                {
                    media.stop();
                }
                khoitao();
                media.start();
                btnplay.setBackgroundResource(R.drawable.pause);
                setTime();
            }
        });
        sksong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                media.seekTo(sksong.getProgress());
            }
        });
    }
    private void updateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
                txtstart.setText(dinhdanggio.format(media.getCurrentPosition()));
                sksong.setProgress(media.getCurrentPosition());
                handler.postDelayed(this,1000);
            }
        },100);
    }
    private void Add() {
        arrsong = new ArrayList<>();
        arrsong.add(new song("Cham day noi dau",R.raw.cham_day_noi_dau));
        arrsong.add(new song("Duoi nhung con mua",R.raw.duoi_nhung_con_mua));
        arrsong.add(new song("Em gai mua",R.raw.em_gai_mua));
        arrsong.add(new song("Mot buoc yeu van dam dau",R.raw.mot_buoc_yeu_van_dam_dau));
    }

    private  void setEvent(){
        txtTitle = (TextView) findViewById(R.id.txttitle);
        txtstart = (TextView) findViewById(R.id.txtbegin);
        txtend = (TextView)findViewById(R.id.txtend);
        btnnext = (Button) findViewById(R.id.btn_next);
        btnprev = (Button) findViewById(R.id.btn_prev);
        btnplay = (Button) findViewById(R.id.btn_play);
        btnstop = (Button) findViewById(R.id.btn_stop);
        sksong = (SeekBar)findViewById(R.id.seekbartime);
    }
    private void khoitao(){
        media = MediaPlayer.create(MainActivity.this,arrsong.get(position).getFile());

        txtTitle.setText(arrsong.get(position).getTitle());
    }
    private void setTime(){
        SimpleDateFormat dinhdang = new SimpleDateFormat("mm:ss");
        txtend.setText(dinhdang.format(media.getDuration()));
        sksong.setMax(media.getDuration());
    }
}
