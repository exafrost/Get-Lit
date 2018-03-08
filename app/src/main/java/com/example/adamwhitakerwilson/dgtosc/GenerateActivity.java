package com.example.adamwhitakerwilson.dgtosc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import static android.graphics.Path.Direction.CW;

public class GenerateActivity extends AppCompatActivity {

    float scaleX, scaleY;
    int viewHieght, viewWidth;

    GenerateView imageView;

    private RadioButton forward;
    private RadioButton backward;
    private RadioButton backwardForward;
    private RadioButton out1;
    private RadioButton out2;
    private RadioButton out3;
    private RadioButton out4;
    private RadioButton out5;
    private RadioButton out6;
    private RadioButton out7;
    private RadioButton id1;
    private RadioButton id2;
    private RadioButton id3;
    private RadioButton id4;
    private RadioButton id5;
    private RadioButton id6;
    private RadioButton id7;
    private RadioButton id8;
    private ToggleButton pause;
    private int check;
    private String ip;
    private int port;
    private int outTrack = 0;
    private int idTrack = 0;
    private boolean pauser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ip = extras.getString("ip");
            port = extras.getInt("port");
            setPort(port);
            setIp(ip);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
         imageView = (GenerateView) findViewById(R.id.signature_canvas);

        forward = (RadioButton) findViewById(R.id.forward);
        backward = (RadioButton) findViewById(R.id.backward);
        backwardForward = (RadioButton) findViewById(R.id.backwardForward);
        backwardForward.setChecked(true);
        pause = (ToggleButton) findViewById(R.id.pauseButton);
        out1 = (RadioButton) findViewById(R.id.out1);
        out2 = (RadioButton) findViewById(R.id.out2);
        out3 = (RadioButton) findViewById(R.id.out3);
        out4 = (RadioButton) findViewById(R.id.out4);
        out5 = (RadioButton) findViewById(R.id.out5);
        out6 = (RadioButton) findViewById(R.id.out6);
        out7 = (RadioButton) findViewById(R.id.out7);
        id1 = (RadioButton) findViewById(R.id.id1);
        id2 = (RadioButton) findViewById(R.id.id2);
        id3 = (RadioButton) findViewById(R.id.id3);
        id4 = (RadioButton) findViewById(R.id.id4);
        id5 = (RadioButton) findViewById(R.id.id5);
        id6 = (RadioButton) findViewById(R.id.id6);
        id7 = (RadioButton) findViewById(R.id.id7);
        id8 = (RadioButton) findViewById(R.id.id8);

        check = 0;
        setSender(check);
        outTrack = 1;
        setTrack(outTrack);
        idTrack = 1;
        setIdentity(idTrack);









        pause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                setPauser(isChecked);
                radioLooper();
            }
        });



        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.loopTypeRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (backward.isChecked()) {
                    check = 2;
                    setSender(check);
                }
                if (backwardForward.isChecked()) {
                    check = 0;
                    setSender(check);
                }
                if (forward.isChecked()) {
                    check = 1;
                    setSender(check);
                }
            }
        });

        final RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.outputs);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (out1.isChecked()) {
                    outTrack = 1;
                    setTrack(outTrack);
                }
                if (out2.isChecked()) {
                    outTrack = 2;
                    setTrack(outTrack);
                }
                if (out3.isChecked()) {
                    outTrack = 3;
                    setTrack(outTrack);
                }
                if (out4.isChecked()) {
                    outTrack = 4;
                    setTrack(outTrack);
                }
                if (out5.isChecked()) {
                    outTrack = 5;
                    setTrack(outTrack);
                }
                if (out6.isChecked()) {
                    outTrack = 6;
                    setTrack(outTrack);
                }
                if (out7.isChecked()) {
                    outTrack = 7;
                    setTrack(outTrack);
                }
            }
        });

        final RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.identitySwitch);
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (id1.isChecked()) {
                    idTrack = 1;
                    setIdentity(idTrack);
                }
                if (id2.isChecked()) {
                    idTrack = 2;
                    setIdentity(idTrack);
                }
                if (id3.isChecked()) {
                    idTrack = 3;
                    setIdentity(idTrack);
                }
                if (id4.isChecked()) {
                    idTrack = 4;
                    setIdentity(idTrack);
                }
                if (id5.isChecked()) {
                    idTrack = 5;
                    setIdentity(idTrack);
                }
                if (id6.isChecked()) {
                    idTrack = 6;
                    setIdentity(idTrack);
                }
                if (id7.isChecked()) {
                    idTrack = 7;
                    setIdentity(idTrack);
                }
                if (id8.isChecked()) {
                    idTrack = 8;
                    setIdentity(idTrack);
                }
            }
        });


    }


    @Override
    protected void onStart() {
       super.onStart();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        scaleX = imageView.getScaleX();
        scaleY = imageView.getScaleY();

        viewHieght = imageView.getMeasuredHeight();
        viewWidth = imageView.getMeasuredWidth();
        setViewHieght(viewHieght);
        setViewWidth(viewWidth);
        Log.d("::::::::::::","Height: " + scaleX + " Width: " + scaleY);
/*

        Bitmap imageBitmap = Bitmap.createBitmap(viewWidth,
                viewHieght, Bitmap.Config.ARGB_8888);
        imageBitmap.eraseColor(Color.BLACK);

        float scale = getResources().getDisplayMetrics().density;
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setTextSize(24*scale);
      //  canvas.drawText("Hello", viewWidth/2,viewHieght/2,p);

        float a = viewWidth/2;

        float b = viewHieght/2;

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



        imageView.setImageBitmap(imageBitmap);


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

    }
    public int getViewHieght(){
        return viewHieght;
    }
   public void setViewHieght(int viewHieght2){
        viewHieght = viewHieght2;
    }
    int getViewWidth(){return viewWidth;}
    void setViewWidth(int viewWidth2){
        viewWidth = viewWidth2;
    }




    // Set the beginning of the next contour to the point (x,y).
    void     moveTo(float x, float y){

    }

    // Add a line from the last point to the specified point (x,y).
    void     lineTo(float x, float y){



    }

    public void setIdentity(int idTrack){
        this.idTrack = idTrack;
    }

    public int getIdTrack(){
        return idTrack;
    }

    public boolean getPauser() {
        return pauser;
    }

    private void setPauser(boolean pauser) {
        this.pauser = pauser;
    }

    public int getTrack() {
        return outTrack;
    }

    private void setTrack(int outTrack) {
        this.outTrack = outTrack;
    }

    public int getPort() {
        return port;
    }

    private void setPort(int portNumber) {
        this.port = portNumber;
    }

    public String getIp() {
        return ip;
    }

    private void setIp(String targetIpStr) {
        this.ip = targetIpStr;
    }

    public int getSender() {
        return check;
    }

    private void setSender(int check) {
        this.check = check;
    }

    public void radioLooper() {

        int whileCount = 0;
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.d("thread is FUCKED", "FUCKED!!!");
            }
            whileCount++;

            if (whileCount == 1) {
                id1.setChecked(true);
            }
            if (whileCount == 2) {
                id2.setChecked(true);
            }
            if (whileCount == 3) {
                id3.setChecked(true);
            }
            if (whileCount == 4) {
                id4.setChecked(true);
            }
            if (whileCount == 5) {
                id5.setChecked(true);
            }
            if (whileCount == 6) {
                id6.setChecked(true);
            }
            if (whileCount == 7) {
                id7.setChecked(true);
            }
            if (whileCount == 8) {
                id8.setChecked(true);
            }
            if (whileCount > 8) {
                whileCount = 0;
            }
            if (!pauser) {
                return;
            }
        }
    }



}