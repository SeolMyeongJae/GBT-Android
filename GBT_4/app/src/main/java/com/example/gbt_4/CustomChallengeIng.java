package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbt_4.dto.GetCustomChallengeDto;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomChallengeIng extends AppCompatActivity {

    Button btn_custom_challenge_ing_status,btn_custom_challenge_ing_chat, btn_custom_challenge_ing_back,btn_custom_challenge_ing_invite;
    ImageView iv_custom_challenge_ing_photo;
    TextView tv_custom_challenge_ing_title,tv_custom_challenge_ing_start, tv_custom_challenge_ing_end,
            tv_custom_challenge_ing_current,tv_custom_challenge_ing_max,tv_custom_challenge_ing_point,
            tv_custom_challenge_ing_description, tv_custom_challenge_ing_bet;

    private String photoURL;

    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_custom_challenge_ing);

        btn_custom_challenge_ing_back= (Button) findViewById(R.id.btn_custom_challenge_ing_back);
        btn_custom_challenge_ing_chat= (Button) findViewById(R.id.btn_custom_challenge_ing_chat);
        btn_custom_challenge_ing_status= (Button) findViewById(R.id.btn_custom_challenge_ing_status);
        btn_custom_challenge_ing_invite = (Button) findViewById(R.id.btn_custom_challenge_ing_invite);

        tv_custom_challenge_ing_title = (TextView) findViewById(R.id.tv_custom_challenge_ing_title);
        tv_custom_challenge_ing_start = (TextView)findViewById(R.id.tv_custom_challenge_ing_start);
        tv_custom_challenge_ing_end = (TextView)findViewById(R.id.tv_custom_challenge_ing_end);
        tv_custom_challenge_ing_current = (TextView)findViewById(R.id.tv_custom_challenge_ing_current);
        tv_custom_challenge_ing_description = (TextView)findViewById(R.id.tv_custom_challenge_ing_description);
        tv_custom_challenge_ing_max = (TextView)findViewById(R.id.tv_custom_challenge_ing_max);
        tv_custom_challenge_ing_bet = (TextView) findViewById(R.id.tv_custom_challenge_ing_bet);

        iv_custom_challenge_ing_photo = (ImageView)findViewById(R.id.iv_custom_challenge_ing_photo);


        //retrofit ??????
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);



        //??? ????????? ???????????? Todo ????????? ID??? Intent??? ?????? ????????? ????????????!
        //?????? ????????? ID??????
        Intent intent = getIntent();
        Long challengeId = intent.getLongExtra("challengeId",0L);
        System.out.println("???????????? ????????? ?????????: ???????????? ????????? Id???"+challengeId+"?????????.");

        //?????? ????????? ???????????? ????????????
        Call<GetCustomChallengeDto> call_customChallenge = retrofitInterface.getCustomChallengeById(challengeId);
        call_customChallenge.enqueue(new Callback<GetCustomChallengeDto>() {
            @Override
            public void onResponse(Call<GetCustomChallengeDto> call, Response<GetCustomChallengeDto> response) {
                if(response.isSuccessful()){
                    try {
                        GetCustomChallengeDto getcustomChallengeDto;
                        getcustomChallengeDto = response.body();
                        tv_custom_challenge_ing_title.setText(getcustomChallengeDto.getTitle());
                        tv_custom_challenge_ing_current.setText(getcustomChallengeDto.getCurrent().toString());
                        tv_custom_challenge_ing_max.setText(getcustomChallengeDto.getMax().toString());
                        tv_custom_challenge_ing_start.setText(getcustomChallengeDto.getStartDate());
                        tv_custom_challenge_ing_end.setText(getcustomChallengeDto.getEndDate());
                        tv_custom_challenge_ing_bet.setText(getcustomChallengeDto.getBet());
                        // TODO: 2022-04-08 endDate - startDate ?????? ??????????????? ????????????
                        tv_custom_challenge_ing_description.setText(getcustomChallengeDto.getDescription());
                        photoURL = getcustomChallengeDto.getImg();
                        Glide.with(CustomChallengeIng.this).load(photoURL).into(iv_custom_challenge_ing_photo);
                        System.out.println("???????????? ?????????????????? ???????????????: ?????? URL???"+photoURL+"?????????.");

                    }catch (Exception e){
                        System.out.println("???????????? ????????? ????????? ???????????????:?????? ??????!"+e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCustomChallengeDto> call, Throwable t) {
                System.out.println("http?????? ??????:"+ t.toString());
            }
        });



        //???????????? ??????
        btn_custom_challenge_ing_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //????????? ?????? ??????
        btn_custom_challenge_ing_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),ChatRoom.class);
                startActivity(intent2);
            }
        });

        //???????????? ?????? ??????
        btn_custom_challenge_ing_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(),CustomChallengeSatus.class);
                startActivity(intent3);
            }
        });


        //???????????? ??????
        btn_custom_challenge_ing_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Invite.class);
                intent.putExtra("challengeId",challengeId);
                startActivity(intent);
            }
        });
    }
}