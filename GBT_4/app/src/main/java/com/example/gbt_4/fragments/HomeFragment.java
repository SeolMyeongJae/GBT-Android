package com.example.gbt_4.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbt_4.CustomChallengeDetail;
import com.example.gbt_4.OfficialChallengeIng;
import com.example.gbt_4.TimerPractice;
import com.example.gbt_4.TodayAttend;
import com.example.gbt_4.R;
import com.example.gbt_4.RetrofitInterface;
import com.example.gbt_4.dto.AddSmokingDto;
import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetUserDto;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment{

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    long millisecondTime, startTime, timeBuff,updateTime = 0L;
    int seconds, minutes, milliSeconds, day, hour;
    Handler handler;

    Dialog dialog;


    TextView timer, tv_userName, tv_comment, tv_todayCount, tv_monthCount,btn_home_go_official_challenge_ing, btn_home_go_custom_challenge_ing;
    Button btn_home_plus, btn_home_attend;
    Long count, nowTime;

    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        handler = new Handler();
        sharedPreferences = this.getActivity().getSharedPreferences("userData",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //dialog ??????
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_add_smoking);

        //?????? ????????????
        nowTime = sharedPreferences.getLong("now",0L);

        //?????? ??????
        if(sharedPreferences.getLong("now", 0L) == 0L) {
            System.out.println(">>>>>>>>>>>" + sharedPreferences.getLong("now", 0L));
            editor.putLong("now", System.currentTimeMillis());
            editor.commit();
        }

        //????????? ??????
        startTime = SystemClock.elapsedRealtime();
        handler.postDelayed(runnable, 0);

        //????????????
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //retrofit ??????
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //ID??? ??????
        tv_userName = (TextView) v.findViewById(R.id.tv_home_name);
        tv_comment = (TextView) v.findViewById(R.id.tv_home_comment);
        tv_todayCount = (TextView) v.findViewById(R.id.tv_today_count);
        btn_home_plus = (Button) v.findViewById(R.id.btn_home_plus);
        btn_home_attend = (Button) v.findViewById(R.id.btn_home_attend);
        btn_home_go_official_challenge_ing = (TextView) v.findViewById(R.id.btn_home_go_official_challenge_ing);
        btn_home_go_custom_challenge_ing = (TextView) v.findViewById(R.id.btn_home_go_custom_challenge_ing);

        // ?????? ?????? ???????????? ??????
        Call<GetUserDto> call_getUser = retrofitInterface.getByUserId(1L);
        call_getUser.enqueue(new Callback<GetUserDto>() {
            @Override
            public void onResponse(Call<GetUserDto> call, Response<GetUserDto> response) {
                if (response.isSuccessful()) {
                    try {
                        Long userId;
                        GetUserDto getUserDto = response.body();
                        tv_comment.setText(getUserDto.getComment());
                        tv_userName.setText(getUserDto.getUserName());

                        //userID??? ?????? ???????????? ??????
                        editor.putLong("userId",getUserDto.getUserId());
                        editor.putString("myName",getUserDto.getUserName());

                        editor.commit();

                        userId = sharedPreferences.getLong("userId",-1);
                        System.out.println("22222222222222222222222222222222"+userId);

                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<GetUserDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });

        //?????? 1??? ????????? ???????????? ??????
        Call<GetSmokingDto> call_getSmokingDto = retrofitInterface.getTodayCount(1L);
        call_getSmokingDto.enqueue(new Callback<GetSmokingDto>() {
            @Override
            public void onResponse(Call<GetSmokingDto> call, Response<GetSmokingDto> response) {
                try {
                    GetSmokingDto getSmokingDto = response.body();

                    if (getSmokingDto == null){
                        tv_todayCount.setText("0");
                    } else {
                        tv_todayCount.setText(getSmokingDto.getCount().toString()+"??????");
                    }
                }catch (Exception e){
                    System.out.println("????????????!");
                }
            }
            @Override
            public void onFailure(Call<GetSmokingDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });

        //?????? ??????
        btn_home_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), TodayAttend.class);
                startActivity(intent1);
            }
        });

        //?????? ?????? ??????
        btn_home_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        //?????? ????????? ????????????
        btn_home_go_official_challenge_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),OfficialChallengeIng.class);
                startActivity(intent);
            }
        });

        //????????? ????????? ????????????
        btn_home_go_custom_challenge_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustomChallengeFragment customChallengeFragment = new CustomChallengeFragment();
                fragmentTransaction.replace(R.id.frame_lo,customChallengeFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        timer = (TextView) v.findViewById(R.id.timer);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimerPractice.class);
                startActivity(intent);
            }
        });
        return v;
    }

    //OKhttp ?????? ????????????
    private HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                android.util.Log.e("MyGitHubData :", message + "");
            }
        });
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    //????????? ?????? ?????? ?????????
    public void restartTimer(){
        millisecondTime = 0L;
        startTime = 0L;
        timeBuff = 0L;
        updateTime = 0L;
        seconds = 0;
        minutes = 0;
        milliSeconds = 0;

        nowTime = System.currentTimeMillis();
        handler.postDelayed(runnable, 0);
        editor.putLong("now",nowTime);
        editor.commit();
    }

    public void addSmoking() {
        AddSmokingDto addSmokingDto = new AddSmokingDto(1L);
        Call<Long> call_addSmoking = retrofitInterface.addSmoking(addSmokingDto);
        call_addSmoking.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (response.isSuccessful()) {
                    try {
                        Toast.makeText(getActivity(), "?????? ??? ????????? ??????????????????...", Toast.LENGTH_SHORT).show();
                        restartTimer();
                        tv_todayCount.setText(response.body().toString()+"??????");
                    }catch (Exception e){
                        System.out.println("?????? ???????????? ?????? ?????? :"+e.getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                System.out.println("?????? ???????????? API?????? ??????: "+t.toString());
            }
        });
    }

    public void showDialog(){
            dialog.show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btn_yes = dialog.findViewById(R.id.btn_add_smoking_yes);
            Button btn_no = dialog.findViewById(R.id.btn_add_smoking_no);
            //????????? ??????
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "??? ???????????????! ????????????", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            //??? ??????
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addSmoking();
                    dialog.dismiss();
                }
            });
    }



    //????????? Runnable
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
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

            timer.setText("" + day + "??? " + hour + "?????? " + minutes + "??? "
                    + String.format("%02d", seconds) + "???");

            handler.postDelayed(this, 0);
        }
    };
}