package tdc.edu.vn.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.jar.Attributes;

public class DrawingView  extends View {

    private Paint drawPaint;
    Point point=new Point();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context,attrs);
        setupPaint();
    }
    private void setupPaint(){
        drawPaint=new Paint();
        drawPaint.setAntiAlias(true);
    }
    public boolean onTouchEvent(MotionEvent event){
        float touchX=event.getX();
        float touchY=event.getY();
        point=new Point(Math.round(touchX),Math.round(touchY));
        invalidate();
        return true;
    }
    protected void onDraw(Canvas canvas){
        drawPaint.setColor(Color.BLUE);
        canvas.drawRect(100,100,200,200,drawPaint);
        drawPaint.setColor(Color.GREEN);
        canvas.drawCircle(point.x,point.y,20,drawPaint);
    }
    public void move(int dk){
        switch (dk){
            case 1:point.y+=5;break;
            case 2:point.y-=5;break;
            case 3:point.x+=5;break;
            case 4:point.x-=5;break;
        }
        invalidate();
    }
}
