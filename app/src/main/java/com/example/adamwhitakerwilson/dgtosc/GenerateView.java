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

/**
 * Created by adamwhitakerwilson on 2017-05-04.
 */

public class GenerateView extends View {
    private int width;
    private int height;
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
    int  maxY, maxX;
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



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        maxX = MeasureSpec.getSize(widthMeasureSpec);
        maxY = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("maxX",Integer.toString(maxX));

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

        width = w;
        height = h;

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

                int count = 0;
                float xTemp, yTemp,y, xFin, yFin;
                xTemp = 0;
                yTemp = 0;
                xFin = 0;
                yFin = 0;
                y=1;
                float x = 1;
                try{
                    Thread.sleep(60);
                }catch (InterruptedException e){
                    Log.d("thread is FUCKED", "FUCKED!!!");
                }
                touch_start(xFin,yFin);

                while(true){

                    try {
                        Thread.sleep(60);
                        xFin = x;
                        yFin = y;
                        // now2 = System.nanoTime()/1000000;
                        //  diff = now2-now1;
                        xTemp = normalizeX(x);
                        yTemp = normalizeY(y);


                        //xTemp = (float)Math.pow(xTemp, 2);
                        // yTemp = (float)Math.sqrt(Math.abs(yTemp));

                        //yTemp = Math.abs(yTemp);
                        xFin = (xTemp*(maxX/2)+maxX/2);
                        yFin = (yTemp*(maxY/2)+maxY/2);
                        touch_move(xFin,yFin);
                        Log.d("x:", Float.toString(xFin));

                        if(count<=maxX) {
                            count++;
                            x+=2;
                            y+=2;
                        }
                        else if(count > maxX) {
                            count--;
                            x-=2;
                            y-=2;
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

}