package tdc.edu.vn.myapplication;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    ImageButton prev,play,next,stop;
    ImageView songPicture;
    SeekBar seekBar;
    TextView txtTenBaiHat, txtDau, txtCuoi;
    MediaPlayer mediaPlayer;
    ArrayList<Song> arrayList;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        khoitao();

        setEvent();
    }

    private void setControl(){
        prev = (ImageButton) findViewById(R.id.prev);
        play = (ImageButton) findViewById(R.id.play);
        next = (ImageButton) findViewById(R.id.next);
        stop = (ImageButton) findViewById(R.id.stop);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        txtTenBaiHat = (TextView) findViewById(R.id.songName);
        txtDau = (TextView) findViewById(R.id.tvdau);
        txtCuoi = (TextView) findViewById(R.id.tvcuoi);
        songPicture = findViewById(R.id.songPicture);
    }

    private void khoitao(){
        arrayList = new ArrayList<Song>();
        arrayList.add(new Song("Anh nhà ở đâu thế", R.drawable.mot, R.raw.anhnhaodau));
        arrayList.add(new Song("Anh ơi ở lại", R.drawable.hai, R.raw.anhoiolai));
        arrayList.add(new Song("Bạc phận", R.drawable.ba, R.raw.bacphan));
        arrayList.add(new Song("Đừng yêu nữa em mệt rồi", R.drawable.bon, R.raw.dungyeunua));
        arrayList.add(new Song("Một bước yêu vạn dậm đau", R.drawable.nam, R.raw.mby));

    }

    private void setMediaPlayer(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, arrayList.get(position).getFile());
        txtTenBaiHat.setText(arrayList.get(position).getTenBaiHat());
        songPicture.setImageResource(arrayList.get(position).getHinhAnh());
    }

    private void setEvent(){
        setMediaPlayer();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.play);
                }
                else{
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.pause);
                }
                setTimeTotal();
                updateTime();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                play.setImageResource(R.drawable.play);

                setMediaPlayer();
                setTimeTotal();
                updateTime();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position > arrayList.size() - 1){
                    position = 0;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                seekBar.setProgress(0);
                setMediaPlayer();
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
                setTimeTotal();
                updateTime();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0){
                    position = arrayList.size() - 1;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                seekBar.setProgress(0);
                setMediaPlayer();
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
                setTimeTotal();
                updateTime();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }
     private void setTimeTotal(){
         SimpleDateFormat dinhBangGio = new SimpleDateFormat("mm:ss");
         txtCuoi.setText(dinhBangGio.format(mediaPlayer.getDuration()) + "");

         seekBar.setMax(mediaPlayer.getDuration());
     }

     private void updateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat currentTime = new SimpleDateFormat("mm:ss");
                txtDau.setText(currentTime.format(mediaPlayer.getCurrentPosition()) + "");

                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > arrayList.size() - 1){
                            position = 0;
                        }

                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        seekBar.setProgress(0);
                        setMediaPlayer();
                        mediaPlayer.start();
                        play.setImageResource(R.drawable.stop);
                        setTimeTotal();
                        updateTime();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);

     }
}
