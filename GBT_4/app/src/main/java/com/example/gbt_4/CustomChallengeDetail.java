package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbt_4.dto.GetCustomChallengeDto;
import com.example.gbt_4.dto.UserCustomChallengeDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomChallengeDetail extends AppCompatActivity {
    Button btn_custom_challenge_detail_back, btn_custom_challenge_detail_join;
    Dialog dialog;
    Long challengeId;
    String photoURL;

    TextView tv_custom_challenge_detail_title,tv_custom_challenge_detail_start,
            tv_custom_challenge_detail_end, tv_custom_challenge_detail_current, tv_custom_challenge_detail_max,
            tv_custom_challenge_detail_description, tv_custom_challenge_detail_bet;
    ImageView iv_custom_challenge_detail_photo;




    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_custom_challenge_detail);

        //dialog 세팅
        dialog = new Dialog(CustomChallengeDetail.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_join);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //챌린지 Id값 받아오기
        Intent intent = getIntent();
        challengeId = intent.getLongExtra("challengeId",0L);
        System.out.println("챌린지 상세페이지: 전해받은 챌린지 ID는 "+challengeId+"입니다");

        tv_custom_challenge_detail_title = (TextView)findViewById(R.id.tv_custom_challenge_detail_title);
        tv_custom_challenge_detail_bet = (TextView)findViewById(R.id.tv_custom_challenge_detail_bet);
        tv_custom_challenge_detail_current = (TextView)findViewById(R.id.tv_custom_challenge_detail_current);
        tv_custom_challenge_detail_max = (TextView)findViewById(R.id.tv_custom_challenge_detail_max);
        tv_custom_challenge_detail_start = (TextView)findViewById(R.id.tv_custom_challenge_detail_start);
        tv_custom_challenge_detail_end = (TextView)findViewById(R.id.tv_custom_challenge_detail_end);
        tv_custom_challenge_detail_description = (TextView)findViewById(R.id.tv_custom_challenge_detail_description);
        iv_custom_challenge_detail_photo = (ImageView)findViewById(R.id.iv_custom_challenge_detail_photo);
        btn_custom_challenge_detail_join = (Button)findViewById(R.id.btn_custom_challenge_detail_join);
        btn_custom_challenge_detail_back = (Button)findViewById(R.id.btn_custom_challenge_detail_back);

        //커스텀 챌린지 상세정보 받아오기
//        Call<GetCustomChallengeDto> call_getCustomChallengeDto = retrofitInterface.getCustomChallengeById(challengeId);
        Call<GetCustomChallengeDto> call_getCustomChallengeDto = retrofitInterface.getCustomChallengeById(1L);
        call_getCustomChallengeDto.enqueue(new Callback<GetCustomChallengeDto>() {
            @Override
            public void onResponse(Call<GetCustomChallengeDto> call, Response<GetCustomChallengeDto> response) {
                if (response.isSuccessful()) {
                    try {
                        GetCustomChallengeDto getCustomChallengeDto;
                        getCustomChallengeDto = response.body();
                        System.out.println("커스텀 챌린지 상세정보: 불러오기 성공");
                        tv_custom_challenge_detail_title.setText(getCustomChallengeDto.getTitle());
                        tv_custom_challenge_detail_bet.setText(getCustomChallengeDto.getBet());
                        tv_custom_challenge_detail_current.setText(getCustomChallengeDto.getCurrent().toString());
                        tv_custom_challenge_detail_max.setText(getCustomChallengeDto.getMax().toString());
                        tv_custom_challenge_detail_start.setText(getCustomChallengeDto.getStartDate());
                        tv_custom_challenge_detail_end.setText(getCustomChallengeDto.getEndDate());
                        tv_custom_challenge_detail_description.setText(getCustomChallengeDto.getDescription());
                        photoURL = getCustomChallengeDto.getImg();
                        Glide.with(CustomChallengeDetail.this).load(photoURL).into(iv_custom_challenge_detail_photo);

                    }catch (Exception e){
                        System.out.println("커스텀 챌린지 상세정보: 예외 오류");
                    }
                }
            }
            @Override
            public void onFailure(Call<GetCustomChallengeDto> call, Throwable t) {
                System.out.println("커스텀 챌린지 상세정보: API통신 실패: "+t.toString());
            }
        });
        


        //뒤로가기 버튼
        btn_custom_challenge_detail_back = (Button) findViewById(R.id.btn_custom_challenge_detail_back);
        btn_custom_challenge_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //초대 수락하기 버튼
        btn_custom_challenge_detail_join =(Button) findViewById(R.id.btn_custom_challenge_detail_join);
        btn_custom_challenge_detail_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }

            private void showDialog() {
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btn_yes = dialog.findViewById(R.id.btn_yes);
                Button btn_no = dialog.findViewById(R.id.btn_no);

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),CustomChallengeIng.class);
                        intent.putExtra("challengeId",challengeId);
                        System.out.println("챌린지 상세페이지: 전해줄 챌린지 ID는 "+challengeId+"입니다");
                        UserCustomChallengeDto userCustomChallengeDto = new UserCustomChallengeDto();
                        Call<Integer> call_joinCustomChallenge = retrofitInterface.joinCustomChallenge(userCustomChallengeDto);
                        call_joinCustomChallenge.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if (response.isSuccessful()) {
                                    try {
                                        System.out.println("커스텀 챌린지 상세 페이지: 참여가 완료 되었습니다");
                                    }catch (Exception e){
                                        System.out.println("커스텀 챌린지 상세 페이지 참여 오류: "+e.getMessage());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                System.out.println("커스텀 챌린지 상세 페이지 참여 API통신 실패: "+t.toString());
                            }
                        });
                        startActivity(intent);
                        dialog.dismiss();
                        finish();
                    }
                });

                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}