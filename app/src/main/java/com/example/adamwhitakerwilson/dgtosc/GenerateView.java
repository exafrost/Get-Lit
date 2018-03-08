package com.example.adamwhitakerwilson.dgtosc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by adamwhitakerwilson on 2017-05-04.
 */

public class GenerateView extends View {
   // private int width;
   // private int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private final Paint mPaint;
    private final Path mPath;
    private final Paint mBitmapPaint;
    private final Paint circlePaint;
    private final Path circlePath;
    private float mX, mY;
    MyBitmapFactory bitMapFac = null;
    int viewHieght, viewWidth;
    int  maxY, maxX, t;

    //network variables
    private OSCPortOut sender1 = null;
    private OSCPortOut sender2 = null;
    private OSCPortOut sender3 = null;
    private OSCPortOut sender4 = null;
    private OSCPortOut sender5 = null;
    private OSCPortOut sender6 = null;
    private OSCPortOut sender7 = null;
    private OSCPortOut sender8 = null;
    private InetAddress targetIP;
    //maths
    private final float CNeg315 = (float) Math.cos(-315);
    private final float SNeg315 = (float) Math.sin(-315);
    private final float C180 = (float) Math.cos(180);
    private final float S180 = (float) Math.sin(180);
    private final float CNeg135 = (float) Math.cos(-135);
    private final float SNeg135 = (float) Math.sin(-135);
    private final float C315 = (float) Math.cos(315);
    private final float S315 = (float) Math.sin(315);
    //points
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private float x3;
    private float y3;
    private float x4;
    private float y4;
    private float x5;
    private float y5;
    private float x6;
    private float y6;
    private float x7;
    private float y7;
    private float x8;
    private float y8;
    //measurements
    private int width;
    private int height;
    private long timeDifferenceTotal;
    private int radio;
    private int radioTrack;
   // private int maxX = 500;
   // private int maxY = 250;
    private int idTrack;

    public GenerateView(Context context, AttributeSet attrst) {
        super(context, attrst);

        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        circlePaint = new Paint();
        circlePath = new Path();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(2f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(2);
        //viewHieght = ((GenerateActivity) getContext()).getViewHieght();
     //   viewWidth = ((GenerateActivity) getContext()).getViewWidth();

        setConnection();

        isInEditMode();
        radioTrack = 1;
        idTrack = 1;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        

        maxX = MeasureSpec.getSize(widthMeasureSpec);
        maxY = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("maxX",Integer.toString(maxX));
        Log.d("maxY",Integer.toString(maxY));

    }
    private void touch_start(float x, float y) {


        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;

    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
            circlePath.reset();
            circlePath.addCircle(mX, mY, 10, Path.Direction.CW);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
      //  Log.d("SizeChanged", Integer.toString(w)+" : " +Integer.toString(h));
        width = w;
        height = h;
        t = 0;

        final float a, b;

        a = maxX / 2;
        b = maxY / 2;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mBitmap.eraseColor(Color.BLACK);
        mCanvas = new Canvas(mBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3F);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        mCanvas.drawLine(a, b - (b / 2), a, b + (b / 2), paint);
        mCanvas.drawLine((a / 2) + (a / 4), b, (maxX * 0.75f) - (a / 4), b, paint);
        mCanvas.drawCircle(a, b, 200F, paint);

        new Thread(new Runnable() {
            public void run() {
                boolean on = false;
                int count = 0;
                float xTemp, yTemp,y, xFin, yFin;
                xTemp = 0;
                yTemp = 0;
                xFin = 0;
                yFin = 0;
                y=0;
                float x = 0;
                try{
                    Thread.sleep(60);
                }catch (InterruptedException e){
                    Log.d("thread is FUCKED", "FUCKED!!!");
                }
                touch_start(xFin,yFin);

                while(true){//pauser boolean

                    try {
                        Thread.sleep(30);
                        count++;
                        x = (x +2)%maxX ;
                        y = (y +2)%maxY;
                       // t++;
                        xFin = x;
                        yFin = y;
                        // now2 = System.nanoTime()/1000000;
                        //  diff = now2-now1;
                        xTemp = normalizeX(x);
                        yTemp = normalizeY(y);
                        //spiro stuff
                      //  xTemp = (radiusFixed + radiusMoving)*Math.cos(t) - offset*Math.cos(((R+r)/r)*t);
                      //  yTemp = (R + r)*Math.sin(t) - O*Math.sin(((R+r)/r)*t);


                        xTemp = (float)Math.pow(xTemp, 2);
                        // yTemp = (float)Math.sqrt(Math.abs(yTemp));

                       // yTemp = Math.abs(yTemp);
                        x1 = xTemp;
                        y1 = yTemp;
                        xFin = ((xTemp+1)*maxX/2);
                        yFin = (((yTemp*(-1)+1))*maxY/2);



                        adamsMath();
                        sendMyOscMessage();
                        touch_move(xFin,yFin);

                      //  Log.d("x:", Float.toString(x1));
                      //  Log.d("y:", Float.toString(y1));
                       // Log.d("count:", Integer.toString(count));
                        if(count >= maxX) {
                            count=0;
                            x = 0;
                            y = 0;
                            xTemp = 0;
                            yTemp = 0;
                            xFin = 0;
                            yFin = 0;
                          //  Log.d("count maxX", Integer.toString(count)+" : "+ Integer.toString(maxX) );

                        }




                        //canvas.drawPoint(xTemp, yTemp, paint);

                        //System.out.println("Diff: "+diff);

                        //  System.out.println("Count: "+count);
                        //  System.out.println("X: "+x);
                        //  System.out.println("Y: "+y);
                    }
                    catch(InterruptedException e) {
                        // do something with e
                        Log.d("thread is FUCKED", "FUCKED!!!");
                    }

                }

            }

        }).start();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPath(circlePath, circlePaint);



    /*instantiate a bitmap and draw stuff here, it could well be another
    class which you systematically update via a different thread so that you can get a fresh updated
    bitmap from, that you desire to be updated onto the custom ImageView.
   That will happen everytime onDraw has received a call i.e. something like:*/
//        Bitmap myBitMap = bitMapFac.update(); //where update returns the most up  to date Bitmap
        //here you set the rectangles in which you want to draw the bitmap and pass the bitmap

    //    canvas.drawBitmap(myBitMap, new Rect(0,0,400,400), new Rect(0,0,240,135) , null);

        //you need to call postInvalidate so that the system knows that it  should redraw your custom ImageView
        this.postInvalidate();
    }

    //touch scale normalizers from {[min(x,y)] to [max(x,y)]} to {[-1.0 to 1.0]}

    private float normalizeX(float n) {

        n = n / (maxX >> 1) - 1f;

        return n;
    }

    private float normalizeY(float n) {

        n = (n / (maxY >> 1) - 1f) * (-1f);

        return n;
    }

    //send OSC messages

    private void sendMyOscMessage() {

        try {



            idTrack = ((GenerateActivity) getContext()).getIdTrack();
            radioTrack = ((GenerateActivity) getContext()).getTrack();
            OSCMessage msgX1 = new OSCMessage();
            OSCMessage msgY1 = new OSCMessage();
            OSCMessage msgX2 = new OSCMessage();
            OSCMessage msgY2 = new OSCMessage();
            OSCMessage msgX3 = new OSCMessage();
            OSCMessage msgY3 = new OSCMessage();
            OSCMessage msgX4 = new OSCMessage();
            OSCMessage msgY4 = new OSCMessage();
            OSCMessage msgX5 = new OSCMessage();
            OSCMessage msgY5 = new OSCMessage();
            OSCMessage msgX6 = new OSCMessage();
            OSCMessage msgY6 = new OSCMessage();
            OSCMessage msgX7 = new OSCMessage();
            OSCMessage msgY7 = new OSCMessage();
            OSCMessage msgX8 = new OSCMessage();
            OSCMessage msgY8 = new OSCMessage();
            msgX1.setAddress("/X_1");
            msgY1.setAddress("/Y_1");
            msgX2.setAddress("/X_2");
            msgY2.setAddress("/Y_2");
            msgX3.setAddress("/X_3");
            msgY3.setAddress("/Y_3");
            msgX4.setAddress("/X_4");
            msgY4.setAddress("/Y_4");
            msgX5.setAddress("/X_5");
            msgY5.setAddress("/Y_5");
            msgX6.setAddress("/X_6");
            msgY6.setAddress("/Y_6");
            msgX7.setAddress("/X_7");
            msgY7.setAddress("/Y_7");
            msgX8.setAddress("/X_8");
            msgY8.setAddress("/Y_8");



            switch (idTrack) {

                case 1: {
                    switch (radioTrack) {
                        case 1: {
                            msgX1.addArgument(x1);
                            msgY1.addArgument(y1);
                            sender1.send(msgX1);
                            sender1.send(msgY1);
                            break;
                        }
                        case 2: {
                            msgX1.addArgument(x1);
                            msgY1.addArgument(y1);
                            sender1.send(msgX1);
                            sender1.send(msgY1);

                            msgX4.addArgument(x4);
                            msgY4.addArgument(y4);
                            sender4.send(msgX4);
                            sender4.send(msgY4);
                            break;
                        }
                        case 3: {
                            msgX1.addArgument(x1);
                            msgY1.addArgument(y1);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX3.addArgument(x3);
                            msgY3.addArgument(y3);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX7.addArgument(x7);
                            msgY7.addArgument(y7);
                            sender7.send(msgX7);
                            sender7.send(msgY7);
                            break;
                        }
                        case 4: {
                            msgX1.addArgument(x1);
                            msgY1.addArgument(y1);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX6.addArgument(x6);
                            msgY6.addArgument(y6);
                            sender6.send(msgX6);
                            sender6.send(msgY6);
                            break;
                        }
                        case 5: {

                            msgX1.addArgument(x1);
                            msgY1.addArgument(y1);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX2.addArgument(x2);
                            msgY2.addArgument(y2);
                            sender2.send(msgX2);
                            sender2.send(msgY2);
                            break;
                        }
                        case 6: {

                            msgX1.addArgument(x1);
                            msgY1.addArgument(y1);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX3.addArgument(x3);
                            msgY3.addArgument(y3);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX5.addArgument(x5);
                            msgY5.addArgument(y5);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX7.addArgument(x7);
                            msgY7.addArgument(y7);
                            sender7.send(msgX7);
                            sender7.send(msgY7);
                            break;
                        }
                        case 7: {

                            msgX1.addArgument(x1);
                            msgY1.addArgument(y1);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX2.addArgument(x2);
                            msgY2.addArgument(y2);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX3.addArgument(x3);
                            msgY3.addArgument(y3);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX4.addArgument(x4);
                            msgY4.addArgument(y4);
                            sender4.send(msgX4);
                            sender4.send(msgY4);

                            msgX5.addArgument(x5);
                            msgY5.addArgument(y5);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX6.addArgument(x6);
                            msgY6.addArgument(y6);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX7.addArgument(x7);
                            msgY7.addArgument(y7);
                            sender7.send(msgX7);
                            sender7.send(msgY7);

                            msgX8.addArgument(x8);
                            msgY8.addArgument(y8);
                            sender8.send(msgX8);
                            sender8.send(msgY8);
                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    break;
                }
                case 2: {

                    switch (radioTrack) {
                        case 1: {


                            msgX2.addArgument(x1);
                            msgY2.addArgument(y1);
                            sender2.send(msgX2);
                            sender2.send(msgY2);
                            break;
                        }
                        case 2: {

                            msgX2.addArgument(x1);
                            msgY2.addArgument(y1);
                            sender2.send(msgX2);
                            sender2.send(msgY2);

                            msgX5.addArgument(x4);
                            msgY5.addArgument(y4);
                            sender5.send(msgX5);
                            sender5.send(msgY5);
                            break;
                        }
                        case 3: {

                            msgX2.addArgument(x1);
                            msgY2.addArgument(y1);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX4.addArgument(x3);
                            msgY4.addArgument(y3);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX8.addArgument(x7);
                            msgY8.addArgument(y7);
                            sender8.send(msgX8);
                            sender8.send(msgY8);
                            break;
                        }
                        case 4: {

                            msgX2.addArgument(x1);
                            msgY2.addArgument(y1);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX7.addArgument(x6);
                            msgY7.addArgument(y6);
                            sender7.send(msgX7);
                            sender7.send(msgY7);
                            break;
                        }
                        case 5: {

                            msgX2.addArgument(x1);
                            msgY2.addArgument(y1);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX3.addArgument(x2);
                            msgY3.addArgument(y2);
                            sender3.send(msgX3);
                            sender3.send(msgY3);
                            break;
                        }
                        case 6: {

                            msgX2.addArgument(x1);
                            msgY2.addArgument(y1);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX4.addArgument(x3);
                            msgY4.addArgument(y3);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX6.addArgument(x5);
                            msgY6.addArgument(y5);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX8.addArgument(x7);
                            msgY8.addArgument(y7);
                            sender8.send(msgX8);
                            sender8.send(msgY8);
                            break;
                        }
                        case 7: {

                            msgX2.addArgument(x1);
                            msgY2.addArgument(y1);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX3.addArgument(x2);
                            msgY3.addArgument(y2);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX4.addArgument(x3);
                            msgY4.addArgument(y3);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX5.addArgument(x4);
                            msgY5.addArgument(y4);
                            sender5.send(msgX5);
                            sender5.send(msgY5);

                            msgX6.addArgument(x5);
                            msgY6.addArgument(y5);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX7.addArgument(x6);
                            msgY7.addArgument(y6);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX8.addArgument(x7);
                            msgY8.addArgument(y7);
                            sender8.send(msgX8);
                            sender8.send(msgY8);

                            msgX1.addArgument(x8);
                            msgY1.addArgument(y8);
                            sender1.send(msgX1);
                            sender1.send(msgY1);
                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    break;
                }
                case 3: {
                    switch (radioTrack) {
                        case 1: {


                            msgX3.addArgument(x1);
                            msgY3.addArgument(y1);
                            sender3.send(msgX3);
                            sender3.send(msgY3);
                            break;
                        }
                        case 2: {

                            msgX3.addArgument(x1);
                            msgY3.addArgument(y1);
                            sender3.send(msgX3);
                            sender3.send(msgY3);

                            msgX6.addArgument(x4);
                            msgY6.addArgument(y4);
                            sender6.send(msgX6);
                            sender6.send(msgY6);
                            break;
                        }
                        case 3: {

                            msgX3.addArgument(x1);
                            msgY3.addArgument(y1);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX5.addArgument(x3);
                            msgY5.addArgument(y3);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX1.addArgument(x7);
                            msgY1.addArgument(y7);
                            sender1.send(msgX1);
                            sender1.send(msgY1);
                            break;
                        }
                        case 4: {

                            msgX3.addArgument(x1);
                            msgY3.addArgument(y1);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX8.addArgument(x6);
                            msgY8.addArgument(y6);
                            sender8.send(msgX8);
                            sender8.send(msgY8);
                            break;
                        }
                        case 5: {

                            msgX3.addArgument(x1);
                            msgY3.addArgument(y1);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX4.addArgument(x2);
                            msgY4.addArgument(y2);
                            sender4.send(msgX4);
                            sender4.send(msgY4);
                            break;
                        }
                        case 6: {

                            msgX3.addArgument(x1);
                            msgY3.addArgument(y1);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX5.addArgument(x3);
                            msgY5.addArgument(y3);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX7.addArgument(x5);
                            msgY7.addArgument(y5);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX1.addArgument(x7);
                            msgY1.addArgument(y7);
                            sender1.send(msgX1);
                            sender1.send(msgY1);
                            break;
                        }
                        case 7: {

                            msgX3.addArgument(x1);
                            msgY3.addArgument(y1);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX4.addArgument(x2);
                            msgY4.addArgument(y2);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX5.addArgument(x3);
                            msgY5.addArgument(y3);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX6.addArgument(x4);
                            msgY6.addArgument(y4);
                            sender6.send(msgX6);
                            sender6.send(msgY6);

                            msgX7.addArgument(x5);
                            msgY7.addArgument(y5);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX8.addArgument(x6);
                            msgY8.addArgument(y6);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX1.addArgument(x7);
                            msgY1.addArgument(y7);
                            sender1.send(msgX1);
                            sender1.send(msgY1);

                            msgX2.addArgument(x8);
                            msgY2.addArgument(y8);
                            sender2.send(msgX2);
                            sender2.send(msgY2);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    switch (radioTrack) {
                        case 1: {


                            msgX4.addArgument(x1);
                            msgY4.addArgument(y1);
                            sender4.send(msgX4);
                            sender4.send(msgY4);
                            break;
                        }
                        case 2: {

                            msgX4.addArgument(x1);
                            msgY4.addArgument(y1);
                            sender4.send(msgX4);
                            sender4.send(msgY4);

                            msgX7.addArgument(x4);
                            msgY7.addArgument(y4);
                            sender7.send(msgX7);
                            sender7.send(msgY7);
                            break;
                        }
                        case 3: {

                            msgX4.addArgument(x1);
                            msgY4.addArgument(y1);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX6.addArgument(x3);
                            msgY6.addArgument(y3);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX2.addArgument(x7);
                            msgY2.addArgument(y7);
                            sender2.send(msgX2);
                            sender2.send(msgY2);
                            break;
                        }
                        case 4: {

                            msgX4.addArgument(x1);
                            msgY4.addArgument(y1);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX1.addArgument(x6);
                            msgY1.addArgument(y6);
                            sender1.send(msgX1);
                            sender1.send(msgY1);
                            break;
                        }
                        case 5: {

                            msgX4.addArgument(x1);
                            msgY4.addArgument(y1);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX5.addArgument(x2);
                            msgY5.addArgument(y2);
                            sender5.send(msgX5);
                            sender5.send(msgY5);
                            break;
                        }
                        case 6: {

                            msgX4.addArgument(x1);
                            msgY4.addArgument(y1);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX6.addArgument(x3);
                            msgY6.addArgument(y3);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX8.addArgument(x5);
                            msgY8.addArgument(y5);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX2.addArgument(x7);
                            msgY2.addArgument(y7);
                            sender2.send(msgX2);
                            sender2.send(msgY2);
                            break;
                        }
                        case 7: {

                            msgX4.addArgument(x1);
                            msgY4.addArgument(y1);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX5.addArgument(x2);
                            msgY5.addArgument(y2);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX6.addArgument(x3);
                            msgY6.addArgument(y3);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX7.addArgument(x4);
                            msgY7.addArgument(y4);
                            sender7.send(msgX7);
                            sender7.send(msgY7);

                            msgX8.addArgument(x5);
                            msgY8.addArgument(y5);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX1.addArgument(x6);
                            msgY1.addArgument(y6);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX2.addArgument(x7);
                            msgY2.addArgument(y7);
                            sender2.send(msgX2);
                            sender2.send(msgY2);

                            msgX3.addArgument(x8);
                            msgY3.addArgument(y8);
                            sender3.send(msgX3);
                            sender3.send(msgY3);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    switch (radioTrack) {
                        case 1: {


                            msgX5.addArgument(x1);
                            msgY5.addArgument(y1);
                            sender5.send(msgX5);
                            sender5.send(msgY5);
                            break;
                        }
                        case 2: {

                            msgX5.addArgument(x1);
                            msgY5.addArgument(y1);
                            sender5.send(msgX5);
                            sender5.send(msgY5);

                            msgX8.addArgument(x4);
                            msgY8.addArgument(y4);
                            sender8.send(msgX8);
                            sender8.send(msgY8);
                            break;
                        }
                        case 3: {

                            msgX5.addArgument(x1);
                            msgY5.addArgument(y1);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX7.addArgument(x3);
                            msgY7.addArgument(y3);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX3.addArgument(x7);
                            msgY3.addArgument(y7);
                            sender3.send(msgX3);
                            sender3.send(msgY3);
                            break;
                        }
                        case 4: {

                            msgX5.addArgument(x1);
                            msgY5.addArgument(y1);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX2.addArgument(x6);
                            msgY2.addArgument(y6);
                            sender2.send(msgX2);
                            sender2.send(msgY2);
                            break;
                        }
                        case 5: {

                            msgX5.addArgument(x1);
                            msgY5.addArgument(y1);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX6.addArgument(x2);
                            msgY6.addArgument(y2);
                            sender6.send(msgX6);
                            sender6.send(msgY6);
                            break;
                        }
                        case 6: {

                            msgX5.addArgument(x1);
                            msgY5.addArgument(y1);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX7.addArgument(x3);
                            msgY7.addArgument(y3);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX1.addArgument(x5);
                            msgY1.addArgument(y5);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX3.addArgument(x7);
                            msgY3.addArgument(y7);
                            sender3.send(msgX3);
                            sender3.send(msgY3);
                            break;
                        }
                        case 7: {

                            msgX5.addArgument(x1);
                            msgY5.addArgument(y1);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX6.addArgument(x2);
                            msgY6.addArgument(y2);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX7.addArgument(x3);
                            msgY7.addArgument(y3);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX8.addArgument(x4);
                            msgY8.addArgument(y4);
                            sender8.send(msgX8);
                            sender8.send(msgY8);

                            msgX1.addArgument(x5);
                            msgY1.addArgument(y5);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX2.addArgument(x6);
                            msgY2.addArgument(y6);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX3.addArgument(x7);
                            msgY3.addArgument(y7);
                            sender3.send(msgX3);
                            sender3.send(msgY3);

                            msgX4.addArgument(x8);
                            msgY4.addArgument(y8);
                            sender4.send(msgX4);
                            sender4.send(msgY4);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 6: {
                    switch (radioTrack) {
                        case 1: {


                            msgX6.addArgument(x1);
                            msgY6.addArgument(y1);
                            sender6.send(msgX6);
                            sender6.send(msgY6);
                            break;
                        }
                        case 2: {

                            msgX6.addArgument(x1);
                            msgY6.addArgument(y1);
                            sender6.send(msgX6);
                            sender6.send(msgY6);

                            msgX1.addArgument(x4);
                            msgY1.addArgument(y4);
                            sender1.send(msgX1);
                            sender1.send(msgY1);
                            break;
                        }
                        case 3: {

                            msgX6.addArgument(x1);
                            msgY6.addArgument(y1);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX8.addArgument(x3);
                            msgY8.addArgument(y3);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX4.addArgument(x7);
                            msgY4.addArgument(y7);
                            sender4.send(msgX4);
                            sender4.send(msgY4);
                            break;
                        }
                        case 4: {

                            msgX6.addArgument(x1);
                            msgY6.addArgument(y1);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX3.addArgument(x6);
                            msgY3.addArgument(y6);
                            sender3.send(msgX3);
                            sender3.send(msgY3);
                            break;
                        }
                        case 5: {

                            msgX6.addArgument(x1);
                            msgY6.addArgument(y1);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX7.addArgument(x2);
                            msgY7.addArgument(y2);
                            sender7.send(msgX7);
                            sender7.send(msgY7);
                            break;
                        }
                        case 6: {

                            msgX6.addArgument(x1);
                            msgY6.addArgument(y1);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX8.addArgument(x3);
                            msgY8.addArgument(y3);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX2.addArgument(x5);
                            msgY2.addArgument(y5);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX4.addArgument(x7);
                            msgY4.addArgument(y7);
                            sender4.send(msgX4);
                            sender4.send(msgY4);
                            break;
                        }
                        case 7: {

                            msgX6.addArgument(x1);
                            msgY6.addArgument(y1);
                            sender6.send(msgX6);
                            sender6.send(msgY6);


                            msgX7.addArgument(x2);
                            msgY7.addArgument(y2);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX8.addArgument(x3);
                            msgY8.addArgument(y3);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX1.addArgument(x4);
                            msgY1.addArgument(y4);
                            sender1.send(msgX1);
                            sender1.send(msgY1);

                            msgX2.addArgument(x5);
                            msgY2.addArgument(y5);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX3.addArgument(x6);
                            msgY3.addArgument(y6);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX4.addArgument(x7);
                            msgY4.addArgument(y7);
                            sender4.send(msgX4);
                            sender4.send(msgY4);

                            msgX5.addArgument(x8);
                            msgY5.addArgument(y8);
                            sender5.send(msgX5);
                            sender5.send(msgY5);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 7: {
                    switch (radioTrack) {
                        case 1: {


                            msgX7.addArgument(x1);
                            msgY7.addArgument(y1);
                            sender7.send(msgX7);
                            sender7.send(msgY7);
                            break;
                        }
                        case 2: {

                            msgX7.addArgument(x1);
                            msgY7.addArgument(y1);
                            sender7.send(msgX7);
                            sender7.send(msgY7);

                            msgX2.addArgument(x4);
                            msgY2.addArgument(y4);
                            sender2.send(msgX2);
                            sender2.send(msgY2);
                            break;
                        }
                        case 3: {

                            msgX7.addArgument(x1);
                            msgY7.addArgument(y1);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX1.addArgument(x3);
                            msgY1.addArgument(y3);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX5.addArgument(x7);
                            msgY5.addArgument(y7);
                            sender5.send(msgX5);
                            sender5.send(msgY5);
                            break;
                        }
                        case 4: {

                            msgX7.addArgument(x1);
                            msgY7.addArgument(y1);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX4.addArgument(x6);
                            msgY4.addArgument(y6);
                            sender4.send(msgX4);
                            sender4.send(msgY4);
                            break;
                        }
                        case 5: {

                            msgX7.addArgument(x1);
                            msgY7.addArgument(y1);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX8.addArgument(x2);
                            msgY8.addArgument(y2);
                            sender8.send(msgX8);
                            sender8.send(msgY8);
                            break;
                        }
                        case 6: {

                            msgX7.addArgument(x1);
                            msgY7.addArgument(y1);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX1.addArgument(x3);
                            msgY1.addArgument(y3);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX3.addArgument(x5);
                            msgY3.addArgument(y5);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX5.addArgument(x7);
                            msgY5.addArgument(y7);
                            sender5.send(msgX5);
                            sender5.send(msgY5);
                            break;
                        }
                        case 7: {

                            msgX7.addArgument(x1);
                            msgY7.addArgument(y1);
                            sender7.send(msgX7);
                            sender7.send(msgY7);


                            msgX8.addArgument(x2);
                            msgY8.addArgument(y2);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX1.addArgument(x3);
                            msgY1.addArgument(y3);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX2.addArgument(x4);
                            msgY2.addArgument(y4);
                            sender2.send(msgX2);
                            sender2.send(msgY2);

                            msgX3.addArgument(x5);
                            msgY3.addArgument(y5);
                            sender3.send(msgX3);
                            sender3.send(msgY3);


                            msgX4.addArgument(x6);
                            msgY4.addArgument(y6);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX5.addArgument(x7);
                            msgY5.addArgument(y7);
                            sender5.send(msgX5);
                            sender5.send(msgY5);

                            msgX6.addArgument(x8);
                            msgY6.addArgument(y8);
                            sender6.send(msgX6);
                            sender6.send(msgY6);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 8: {
                    switch (radioTrack) {
                        case 1: {


                            msgX8.addArgument(x1);
                            msgY8.addArgument(y1);
                            sender8.send(msgX8);
                            sender8.send(msgY8);
                            break;
                        }
                        case 2: {

                            msgX8.addArgument(x1);
                            msgY8.addArgument(y1);
                            sender8.send(msgX8);
                            sender8.send(msgY8);

                            msgX3.addArgument(x4);
                            msgY3.addArgument(y4);
                            sender3.send(msgX3);
                            sender3.send(msgY3);
                            break;
                        }
                        case 3: {

                            msgX8.addArgument(x1);
                            msgY8.addArgument(y1);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX2.addArgument(x3);
                            msgY2.addArgument(y3);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX6.addArgument(x7);
                            msgY6.addArgument(y7);
                            sender6.send(msgX6);
                            sender6.send(msgY6);
                            break;
                        }
                        case 4: {

                            msgX8.addArgument(x1);
                            msgY8.addArgument(y1);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX5.addArgument(x6);
                            msgY5.addArgument(y6);
                            sender5.send(msgX5);
                            sender5.send(msgY5);
                            break;
                        }
                        case 5: {

                            msgX8.addArgument(x1);
                            msgY8.addArgument(y1);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX1.addArgument(x2);
                            msgY1.addArgument(y2);
                            sender1.send(msgX1);
                            sender1.send(msgY1);
                            break;
                        }
                        case 6: {

                            msgX8.addArgument(x1);
                            msgY8.addArgument(y1);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX2.addArgument(x3);
                            msgY2.addArgument(y3);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX4.addArgument(x5);
                            msgY4.addArgument(y5);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX6.addArgument(x7);
                            msgY6.addArgument(y7);
                            sender6.send(msgX6);
                            sender6.send(msgY6);
                            break;
                        }
                        case 7: {

                            msgX8.addArgument(x1);
                            msgY8.addArgument(y1);
                            sender8.send(msgX8);
                            sender8.send(msgY8);


                            msgX1.addArgument(x2);
                            msgY1.addArgument(y2);
                            sender1.send(msgX1);
                            sender1.send(msgY1);


                            msgX2.addArgument(x3);
                            msgY2.addArgument(y3);
                            sender2.send(msgX2);
                            sender2.send(msgY2);


                            msgX3.addArgument(x4);
                            msgY3.addArgument(y4);
                            sender3.send(msgX3);
                            sender3.send(msgY3);

                            msgX4.addArgument(x5);
                            msgY4.addArgument(y5);
                            sender4.send(msgX4);
                            sender4.send(msgY4);


                            msgX5.addArgument(x6);
                            msgY5.addArgument(y6);
                            sender5.send(msgX5);
                            sender5.send(msgY5);


                            msgX6.addArgument(x7);
                            msgY6.addArgument(y7);
                            sender6.send(msgX6);
                            sender6.send(msgY6);

                            msgX7.addArgument(x8);
                            msgY7.addArgument(y8);
                            sender7.send(msgX7);
                            sender7.send(msgY7);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }

                default: {
                    break;
                }
            }




        } catch (Exception ignored) {
            Log.d("sending Exception", ignored.toString());
        }

    }

    //set OSC UDP Port Connection

    private void setConnection() {

        int portNumber = ((GenerateActivity) getContext()).getPort();
        String targetIPStr = ((GenerateActivity) getContext()).getIp();
        try {
            targetIP = InetAddress.getByName(targetIPStr);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            sender1 = new OSCPortOut(targetIP, portNumber);
            sender2 = new OSCPortOut(targetIP, portNumber + 1);
            sender3 = new OSCPortOut(targetIP, portNumber + 2);
            sender4 = new OSCPortOut(targetIP, portNumber + 3);
            sender5 = new OSCPortOut(targetIP, portNumber + 4);
            sender6 = new OSCPortOut(targetIP, portNumber + 5);
            sender7 = new OSCPortOut(targetIP, portNumber + 6);
            sender8 = new OSCPortOut(targetIP, portNumber + 7);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    //Rotates {(x2,y2), (x3,y3)...(x8,y8)} by 45 degrees

    private void adamsMath() {

        x2 = (x1 * (CNeg315)) - (y1 * (SNeg315));
        y2 = (x1 * (SNeg315) + (y1 * (CNeg315)));

        x3 = y1;
        y3 = x1 * (-1.0F);

        x4 = (x1 * (C180)) - (y1 * (S180));
        y4 = (x1 * (S180) + (y1 * (C180)));

        x5 = (x1 * (CNeg135)) - (y1 * (SNeg135));
        y5 = (x1 * (SNeg135) + (y1 * (CNeg135)));

        x6 = x2 * (-1.0F);
        y6 = y2 * (-1.0F);

        x7 = y1 * (-1.0F);
        y7 = x1;

        x8 = (x1 * (C315)) - (y1 * (S315));
        y8 = (x1 * (S315) + (y1 * (C315)));

    }

}