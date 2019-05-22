package tdc.edu.vn.myapplication;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    DrawingView drawingView;
    Button btnTop,btnBot,btnPre,btnNext;
    SensorManager sensorManager;
    private Sensor accelerometer;
    private float vibrateThreshold=0;
    private float lastX,lastY,lastZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setSensor();
        setEvent();
    }

    private void setSensor(){
        sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){
            accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold=accelerometer.getMaximumRange()/2;
        }
    }
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            doiHinh(event);
        }
    }
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
    public void doiHinh(SensorEvent event){
        float deltaX= lastX-event.values[0];
        float deltaY=Math.abs(lastY-event.values[1]);
        float deltaZ=Math.abs(lastZ-event.values[2]);
        if(deltaX > vibrateThreshold){
             drawingView.move(4);
        }


        if(deltaY>vibrateThreshold){
            drawingView.move(2);
        }
        lastX=event.values[0];
        lastY=event.values[1];
        lastZ=event.values[2];
    }

    private void setControl(){
        btnTop=(Button) findViewById(R.id.btnTop);
        btnBot=(Button) findViewById(R.id.btnBot);
        btnPre=(Button) findViewById(R.id.btnPre);
        btnNext=(Button) findViewById(R.id.btnNext);
        drawingView=(DrawingView) findViewById(R.id.drawing);
    }
    private void setEvent()
    {
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(2);
            }
        });
        btnBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(1);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(4);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(3);
            }
        });
    }

}
