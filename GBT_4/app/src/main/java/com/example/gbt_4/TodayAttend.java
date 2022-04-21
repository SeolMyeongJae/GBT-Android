package com.example.gbt_4;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gbt_4.dto.TodayAttendDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodayAttend extends Activity {

    private SharedPreferences sharedPreferences;


    Long userId;
    Long count = 0L;
    Button btn_verify_back,btn_verify_submit;
    EditText et_attend_count;


    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_today_attend);

        sharedPreferences = this.getSharedPreferences("userId",MODE_PRIVATE);
        userId = sharedPreferences.getLong("userId",1L);



        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        //담배 갯수 버튼
        et_attend_count = (EditText) findViewById(R.id.et_attend_count);

        //뒤로가기 버튼
        btn_verify_back = (Button) findViewById(R.id.btn_verify_back);
        btn_verify_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //제출 버튼
        btn_verify_submit = (Button) findViewById(R.id.btn_verify_submit);
        btn_verify_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Long.parseLong(et_attend_count.getText().toString());
                TodayAttendDto todayAttendDto = new TodayAttendDto(userId,count);
                Call<Integer> call_attend = retrofitInterface.todayAttend(todayAttendDto);
                call_attend.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            try{
                                Toast.makeText(TodayAttend.this, "출석체크가 완료되었습니다. 100Point 획득!", Toast.LENGTH_SHORT).show();
                                System.out.println("출석체크 Data: userId= "+userId+"이고, count= "+count);
                            }catch (Exception e){
                                System.out.println("출석체크 통신 예외에러: "+e.getMessage());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        System.out.println("출석체크 API통신 실패"+t.toString());
                    }
                });
                finish();
            }
        });
    }
}