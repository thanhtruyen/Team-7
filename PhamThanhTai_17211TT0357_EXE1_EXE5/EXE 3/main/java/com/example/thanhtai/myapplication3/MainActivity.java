package com.example.thanhtai.myapplication3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgmoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgmoto = (ImageView) findViewById(R.id.imgmoto);
        final Animation animationstranlate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        imgmoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationstranlate);
            }
        });
    }
}
