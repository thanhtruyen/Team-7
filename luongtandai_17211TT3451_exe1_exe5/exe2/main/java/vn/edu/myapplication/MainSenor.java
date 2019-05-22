package vn.edu.myapplication;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainSenor extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    private Sensor accelerometer;
    private float vibrateThreshold = 1;
    LinearLayout view;
    ImageView imgHinh1,imgHinh2,imgHinh3;
    float deltaX = 0;
    float deltaY = 0;
    float deltaZ= 0;
    private  float lastX, lastY, lastZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgHinh1 = (ImageView)findViewById(R.id.images1);
        view =(LinearLayout)findViewById(R.id.view);
        setSensor();
    }



    private void setSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!= null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() /5;
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
           if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
               doiHinh(event);
           }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void doiHinh(SensorEvent event){
         deltaX = Math.abs(lastX - event.values[0]);
         deltaY = Math.abs(lastY - event.values[1]);
         deltaZ = Math.abs(lastZ - event.values[2]);
        if(deltaX > vibrateThreshold)
        {
            imgHinh1.setImageResource(R.drawable.anh1);
            view.setBackgroundColor(Color.RED);
        }
        if(deltaY > vibrateThreshold)
        {
            imgHinh1.setImageResource(R.drawable.anh3);
            view.setBackgroundColor(Color.YELLOW);
        }
        if(deltaZ > vibrateThreshold)
        {
            imgHinh1.setImageResource(R.drawable.anh2);
            view.setBackgroundColor(Color.GREEN);
        }
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ= event.values[2];
    }
}
