package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gbt_4.dto.AddCustomChallengeDto;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class CreateCustomChallenge extends AppCompatActivity {
    private final String URL = "http://54.219.40.82/api/";

    Button btn_create_custom_challenge_generate,btn_yes_create_challenge,btn_no_create_challenge;
    Dialog dialog;
    EditText et_create_custom_challenge_title, et_create_custom_challenge_start_date,et_create_custom_challenge_end_date,et_create_custom_challenge_bet,et_create_custom_challenge_max,et_create_custom_challenge_method,
            et_create_custom_challenge_summary,et_create_custom_challenge_description;

    TextView tv_create_custom_challenge_start_date,tv_create_custom_challenge_end_date,tv_create_custom_challenge_start_time,tv_create_custom_challenge_end_time;
    String title, bet, method, summary, description;
    Long max;
    LocalDateTime startDate, endDate;

    String photoURL;


    //    레트로핏 객체 생성
    private Retrofit retrofit;
    //    인터페이스 객체 생성
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_custom_challenge);

        //id값 부여
        tv_create_custom_challenge_start_date = (TextView) findViewById(R.id.tv_create_custom_challenge_start_date);
        tv_create_custom_challenge_end_date = (TextView) findViewById(R.id.tv_create_custom_challenge_end_date);
        tv_create_custom_challenge_start_time = (TextView)findViewById(R.id.tv_create_custom_challenge_start_time);
        tv_create_custom_challenge_end_time = (TextView)findViewById(R.id.tv_create_custom_challenge_end_time);

        et_create_custom_challenge_title = (EditText)findViewById(R.id.et_create_custom_challenge_title);
        et_create_custom_challenge_bet = (EditText)findViewById(R.id.et_create_custom_challenge_bet);
        et_create_custom_challenge_max = (EditText)findViewById(R.id.et_create_custom_challenge_max);
        et_create_custom_challenge_method = (EditText)findViewById(R.id.et_create_custom_challenge_method);
        et_create_custom_challenge_summary = (EditText)findViewById(R.id.et_create_custom_challenge_summary);
        et_create_custom_challenge_description = (EditText)findViewById(R.id.et_create_custom_challenge_description);

        //dialog 세팅
        dialog = new Dialog(CreateCustomChallenge.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_2);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //챌린지 기간 선택 기능
        tv_create_custom_challenge_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartDatePicker(view);
            }
        });

        tv_create_custom_challenge_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartTimePicker(view);
            }
        });

        tv_create_custom_challenge_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndDatePicker(view);
            }
        });

        tv_create_custom_challenge_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndTimePicker(view);
            }
        });






        //'생성'버튼 기능
        btn_create_custom_challenge_generate = (Button) findViewById(R.id.btn_create_custom_challenge_generate);
        btn_create_custom_challenge_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }

            //생성확인 dialog 설정
            private void showDialog() {
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                btn_yes_create_challenge = (Button) dialog.findViewById(R.id.btn_yes_create_challenge);
                btn_no_create_challenge = (Button) dialog.findViewById(R.id.btn_no_create_challenge);

                //'네'버튼 기능
                btn_yes_create_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        title = et_create_custom_challenge_title.getText().toString();
                        bet  = et_create_custom_challenge_bet.getText().toString();
                        method = et_create_custom_challenge_method.getText().toString();
                        summary = et_create_custom_challenge_summary.getText().toString();
                        description = et_create_custom_challenge_description.getText().toString();

                        max = Long.parseLong(et_create_custom_challenge_max.getText().toString());








                        AddCustomChallengeDto addCustomChallengeDto = new AddCustomChallengeDto(1L,description,null,photoURL,max,method,startDate,endDate,summary,title,bet);
                        //입력된 data로 restAPI통신
                        Call<Integer> call_post = retrofitInterface.addCustomChallenge(addCustomChallengeDto);
                        call_post.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if(response.isSuccessful()){
                                    try {
                                        System.out.println("커스텀 챌린지가 성공적으로 생성 되었습니다!");
                                    }catch (NumberFormatException n){
                                        n.printStackTrace();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                System.out.println("커스텀 챌린지 생성 http통신 오류: "+t.getMessage());
                            }
                        });
                    }
                });

                //'아니오'버튼 기능
                btn_no_create_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
    });
}

    //StartDatePicker 호출
    public void showStartDatePicker(View view){
    DialogFragment dialogFragment = new SelectStartDay();
    dialogFragment.show(getSupportFragmentManager(),"startDatePicker");
    }
    //EndDatePicker 호츌
    public void showEndDatePicker(View view){
    DialogFragment dialogFragment = new SelectEndDate();
    dialogFragment.show(getSupportFragmentManager(),"endDatePicker");
    }
    //StartTimePicker 호출
    public void showStartTimePicker(View view){
        DialogFragment dialogFragment = new SelectStartTime();
        dialogFragment.show(getSupportFragmentManager(),"startTimePicker");
    }
    //EndTimePicker 호출
    public void showEndTimePicker(View view){
        DialogFragment dialogFragment = new SelectEndTime();
        dialogFragment.show(getSupportFragmentManager(),"endTimePicker");
    }

    public void processStartDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        tv_create_custom_challenge_start_date.setText(year_string+"년 "+month_string+"월 "+day_string+"일");
    }

    public void processEndDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        tv_create_custom_challenge_end_date.setText(year_string+"년 "+month_string+"월 "+day_string+"일");
    }

    public void processStartTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        tv_create_custom_challenge_start_time.setText(hour+"시 "+minute+"분 부터");
    }

    public void processEndTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        tv_create_custom_challenge_end_time.setText(hour+"시 "+minute+"분 까지");

    }
}

