package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerPractice extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    TextView textView;
    Button start, pause, reset, lap;
    long millisecondTime, startTime, timeBuff,updateTime = 0L;
    Handler handler;
    int seconds, minutes, milliSeconds, day, hour;

    Long nowTime;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_practice);

        textView = (TextView)findViewById(R.id.textView);

        pause = (Button)findViewById(R.id.button2) ;
        reset = (Button)findViewById(R.id.button3);

        handler = new Handler();

        sharedPreferences = getSharedPreferences("userId",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();

        nowTime = sharedPreferences.getLong("now",0L);
        System.out.println(nowTime);
        if (nowTime != null){
            //그대로 실행


        }else {
            nowTime = SystemClock.elapsedRealtime();
            handler.postDelayed(runnable,0);
        }


        LocalDateTime localDateTime = LocalDateTime.now();
//        editor.putString("time",localDateTime.toString());






        //타이머 시작
        startTime = SystemClock.elapsedRealtime();
//        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
        System.out.println(startTime);





//        //시작 버튼
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startTime = SystemClock.uptimeMillis();
//                handler.postDelayed(runnable, 0);
//                reset.setEnabled(false);
//            }
//        });
//
//        //정지 버튼
//        pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                timeBuff += millisecondTime;
//                handler.removeCallbacks(runnable);
//                reset.setEnabled(true);
//            }
//        });

        //시간 체크 버튼
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(textView.getText().toString());
                System.out.println(startTime);
                System.out.println(System.currentTimeMillis());
                System.out.println(nowTime);
            }
        });
//
        //리셋 버튼
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                millisecondTime = 0L;
                startTime = 0L;
                timeBuff = 0L;
                updateTime = 0L;
                seconds = 0;
                minutes = 0;
                milliSeconds = 0;


                startTime = SystemClock.uptimeMillis();

                handler.postDelayed(runnable, 0);
            }
        });
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            millisecondTime = SystemClock.uptimeMillis() - startTime;
            millisecondTime = System.currentTimeMillis() - nowTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int) (updateTime / 1000);

            day = (int) seconds / (60*60*24);
            seconds = seconds % (60*60*24);
            hour = (int) seconds / (60*60);
            seconds = seconds % (60*60);
            minutes = (int) seconds / 60;
            seconds = seconds % 60;

            milliSeconds = (int) (updateTime % 1000);

            textView.setText("" + day + "일" + hour + "시간" + minutes + "분"
                    + String.format("%02d", seconds) + "초");

            handler.postDelayed(this, 0);
        }
    };
}