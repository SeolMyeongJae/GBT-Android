package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmokingPlus extends Activity {

    Button btn_smoking_yes, btn_smoking_no;

    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_smoking_plus);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);



        //참을게요 버튼
        btn_smoking_no = (Button) findViewById(R.id.btn_smoking_no);
        btn_smoking_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SmokingPlus.this, "잘 참으셨어요!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

//
//        //필게요 버튼
//        btn_smoking_yes = (Button) findViewById(R.id.btn_smoking_yes);
//        btn_smoking_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<Long> call_addSmoking = retrofitInterface.addSmoking(1);
//                call_addSmoking.enqueue(new Callback<Long>() {
//                    @Override
//                    public void onResponse(Call<Long> call, Response<Long> response) {
//                        if (response.isSuccessful()) {
//                            try{
//                                System.out.println(response.body().toString());
//                                Toast.makeText(SmokingPlus.this, "금연.. 쉽지않지만 좀더 노력해보죠!", Toast.LENGTH_SHORT).show();
//                            }catch (Exception e){
//                                System.out.println("예외발생: "+e.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<Long> call, Throwable t) {
//                        System.out.println("api통신 실패: "+t.toString());
//                    }
//                }); finish();
//            }
//        });
    }
}