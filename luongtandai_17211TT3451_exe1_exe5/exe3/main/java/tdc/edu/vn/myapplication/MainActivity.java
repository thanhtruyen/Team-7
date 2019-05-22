package tdc.edu.vn.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView img;
Button run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.run);
                AnimationDrawable anim = (AnimationDrawable)img.getDrawable();
                anim.start();
            }
        });
    }
    public void setControl()
    {
        img =(ImageView)findViewById(R.id.img);
        run = (Button)findViewById(R.id.run);
    }
}
