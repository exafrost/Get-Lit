package com.example.adamwhitakerwilson.dgtosc;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import static android.graphics.Path.Direction.CW;
import static java.security.AccessController.getContext;

/**
 * Created by adamwhitakerwilson on 2017-05-04.
 */

public class MyBitmapFactory {

    Bitmap bitmap = null;

int viewHieght = 200;
    int viewWidth = 200;
    MyBitmapFactory bitMapFac;

    MyBitmapFactory(){

       //  bitMapFac = new MyBitmapFactory();

bitMapFac.update();
setBitmapFactory(bitMapFac);



    }
    public void setBitmapFactory(MyBitmapFactory bitMapFac)
    {
        this.bitMapFac = bitMapFac;
    }

    public Bitmap update() {

        //   int viewHieght = ((GenerateActivity) getContext()).getViewHieght();
        //   int viewWidth = ((GenerateActivity) getContext()).getViewHieght();
        Log.d("::::::::::::", "Height: " + viewHieght + " Width: " + viewWidth);


        Bitmap imageBitmap = Bitmap.createBitmap(viewWidth,
                viewHieght, Bitmap.Config.ARGB_8888);
        imageBitmap.eraseColor(Color.BLACK);

        //float scale = getResources().getDisplayMetrics().density;
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        // p.setTextSize(24*scale);
        //  canvas.drawText("Hello", viewWidth/2,viewHieght/2,p);

        float a = viewWidth / 2;

        float b = viewHieght / 2;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3F);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        Canvas canvas = new Canvas(imageBitmap);
        canvas.drawLine(a, b - (b / 2), a, b + (b / 2), paint);
        canvas.drawLine((a / 2) + (a / 4), b, (viewWidth * 0.75f) - (a / 4), b, paint);
        canvas.drawCircle(a, b, 200F, paint);


        Path path = new Path();
        path.addCircle(40F, 40F, 40F, CW);

bitmap = imageBitmap;



/*
        int count = 1;
        byte x = 1;
        byte y = 1;
        float xTemp;
        float yTemp;

        long now1 = System.nanoTime() / 1000000;
        long now2, diff;


        while(true){

            try {
                Thread.sleep(100);
                // now2 = System.nanoTime()/1000000;
                //  diff = now2-now1;
                pos=count;


                if(count<=128) {
                    count++;
                    x++;
                    y++;
                }
                else if(count > 128) {
                    count = 0;
                    x = 0;
                    y = 0;
                }



                xTemp = (float)x / (128 >> 1) - 1;
                yTemp = ((float)y / (128 >> 1) - 1);

                //xTemp = (float)Math.pow(xTemp, 2);
                yTemp = (float)Math.sqrt(Math.abs(yTemp));
                xTemp = x+(xTemp*128);
                yTemp = y+(yTemp*128);
                //canvas.drawPoint(xTemp, yTemp, paint);

                //System.out.println("Diff: "+diff);

                //  System.out.println("Count: "+count);
                //  System.out.println("X: "+x);
                //  System.out.println("Y: "+y);
            }
            catch(InterruptedException e) {
                // do something with e
            }

        }
*/



 return bitmap;

    }

}
