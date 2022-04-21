package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gbt_4.dto.ChallengeAttendDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChallengeAttend extends Activity {

    private SharedPreferences sharedPreferences;


    String memo;
    Long userId;
    Button btn_challenge_attend_back,btn_challenge_attend_submit;
    EditText et_challenge_attend_memo;

    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_challenge_attend);


        sharedPreferences = this.getSharedPreferences("userId",MODE_PRIVATE);
        userId = sharedPreferences.getLong("userId",1L);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        et_challenge_attend_memo = (EditText) findViewById(R.id.et_challenge_attend_memo);
        btn_challenge_attend_submit = (Button) findViewById(R.id.btn_challenge_attend_submit);

        btn_challenge_attend_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memo = et_challenge_attend_memo.getText().toString();
                ChallengeAttendDto challengeAttendDto = new ChallengeAttendDto(userId,memo);

                Call<Integer> call_challenge_attend = retrofitInterface.challengeAttend(challengeAttendDto);
                call_challenge_attend.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            try{
                                Toast.makeText(ChallengeAttend.this, "오늘의 금연 인증이 완료되었습니다. 50point 획득", Toast.LENGTH_SHORT).show();
                                System.out.println("금연 인증 정보: memo="+memo+", userId="+userId+"입니다");
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        System.out.println("금연 인증 통신실패:"+t.toString());

                    }
                });
                finish();
            }
        });

        //팝업 닫기 버튼
        btn_challenge_attend_back = (Button) findViewById(R.id.btn_challenge_attend_back);
        btn_challenge_attend_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}