package com.example.wolf.hextime;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.main);
        startTimerThread();

    }
    void startTimerThread() {
        Thread th = new Thread(new Runnable() {
            volatile boolean flag = true;
            public void run() {
                while(flag){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Calendar c = Calendar.getInstance();
                            int hr = c.get(Calendar.HOUR);
                            int min = c.get(Calendar.MINUTE);
                            int seconds = c.get(Calendar.SECOND);
                            String hr_s, min_s, seconds_s;
                            if(hr<=9) {
                                hr_s = "0" + hr;
                            }
                            else{
                                hr_s = Integer.toString(hr);
                            }
                            if(min<=9){
                                min_s = "0"+min;
                            }
                            else{
                                min_s = Integer.toString(min);
                            }
                            if(seconds<=9){
                                seconds_s = "0"+seconds;
                            }else{
                                seconds_s = Integer.toString(seconds);
                            }

                            String hex = "#" + hr_s + min_s + seconds_s ;
                            String mytime = hr_s+ " : " + min_s + " : " + seconds_s;
                            //String mytime = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                            TextView txtView = (TextView) findViewById(R.id.timelbl);
                            txtView.setText(mytime);
                            RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.activity_main);
                            bgElement.setBackgroundColor(Color.parseColor(hex));
                        }
                    });

                    try{
                        java.lang.Thread.sleep(999);
                    }
                    catch (Exception e){
                        //Single activity
                        System.exit(0);
                    }
                }
            }
        });
        th.start();
    }
}
