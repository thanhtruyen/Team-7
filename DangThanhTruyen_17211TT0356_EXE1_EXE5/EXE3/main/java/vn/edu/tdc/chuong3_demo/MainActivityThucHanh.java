package vn.edu.tdc.chuong3_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivityThucHanh extends AppCompatActivity {
    ImageView imAirPlane, imTruck, imMatTroi, imQuat, imNhayDen;
    Animation animTruck,animRose,animSun,animPlane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thuchanh);
        setControl();
        setEvent();

    }

    private void setAnimationNhayDen() {
        Toast.makeText(this, "Animation:setAnimationNhayDen", Toast.LENGTH_SHORT).show();

    }

    private void setAnimationMatTroi() {
        animSun=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        imMatTroi.startAnimation(animSun);
    }

    private void setAnimationRose() {
        animRose=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        imQuat.startAnimation(animRose);

    }

    private void setAnimationTruck() {
        animTruck=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        imTruck.startAnimation(animTruck);
    }

    private void setAnimationAirPlane() {
        animPlane=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.plane);
        imAirPlane.startAnimation(animPlane);
    }

    public void setEvent() {
        imAirPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationAirPlane();
            }
        });

        imTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationTruck();
            }
        });


        imNhayDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationNhayDen();
            }
        });

        imQuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationRose();
            }
        });

        imMatTroi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationMatTroi();
            }
        });



    }

    void setControl() {
        imAirPlane = findViewById(R.id.imAirPlane);
        imTruck = findViewById(R.id.imTruck);
        imMatTroi = findViewById(R.id.imMatTroi);
        imQuat = findViewById(R.id.imQuat);
        imNhayDen = findViewById(R.id.imNhayDen);

    }
}
