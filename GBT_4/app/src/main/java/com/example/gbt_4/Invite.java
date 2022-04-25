package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gbt_4.dto.InviteDto;
import com.example.gbt_4.dto.SearchUserDto;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Invite extends Activity {

    String photoURL, callerName, title;
    Long userId, callerId;

    Button btn_invite_back, btn_invite_search,btn_invite_invite, btn_invite_delete;
    EditText et_invite_title, et_invite_user_name;
    TextView tv_invite_user_name, tv_invite_user_id;
    ImageView iv_invite_photo;

    private SharedPreferences sharedPreferences;


    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_invite);

        iv_invite_photo = (ImageView)findViewById(R.id.iv_invite_photo);
        tv_invite_user_name = (TextView)findViewById(R.id.tv_invite_user_name);
        tv_invite_user_id = (TextView) findViewById(R.id.tv_invite_user_id);
        et_invite_title = (EditText)findViewById(R.id.et_invite_title);
        et_invite_user_name = (EditText) findViewById(R.id.et_invite_user_name);

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);



        sharedPreferences = getSharedPreferences("userId", MODE_PRIVATE);
        callerName = sharedPreferences.getString("myName","");
        callerId = sharedPreferences.getLong("userid",1L);
        System.out.println("챌린지 초대: 가져온 사용자 이름= "+callerName);
        System.out.println("챌린지 초대: 가져온 사용자 ID= "+callerId);


        Intent intent = getIntent();
        Long challengeId = intent.getLongExtra("challengeId",0L);
        System.out.println("챌린지 초대: 가져온 챌린지 ID= "+challengeId);


        //유저 찾기 버튼
        btn_invite_search = (Button) findViewById(R.id.btn_invite_search);
        btn_invite_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Call<SearchUserDto> call_search = retrofitInterface.searchUser(et_invite_user_name.getText().toString());
                call_search.enqueue(new Callback<SearchUserDto>() {
                    @Override
                    public void onResponse(Call<SearchUserDto> call, Response<SearchUserDto> response) {
                        if (response.isSuccessful()) {
                            try {
                                SearchUserDto searchUserDto = new SearchUserDto();
                                searchUserDto = response.body();
                                photoURL = searchUserDto.getPhoto();
                                tv_invite_user_name.setText(searchUserDto.getUserName());
                                tv_invite_user_id.setText(""+searchUserDto.getUserId());
                                btn_invite_delete.setVisibility(view.VISIBLE);

                            }catch (Exception e){
                                System.out.println("유저 검색 예외발생: "+e.getMessage());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<SearchUserDto> call, Throwable t) {
                        System.out.println("유저 검색 API통신 실패: "+t.toString());
                    }
                });
            }
        });



        btn_invite_delete = (Button) findViewById(R.id.btn_invite_delete);
        btn_invite_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_invite_photo.setImageResource(R.drawable.empty_picture);
                tv_invite_user_name.setText("");
                tv_invite_user_id.setText("");
                btn_invite_delete.setVisibility(view.GONE);
            }
        });


        //뒤로가기 버튼
        btn_invite_back = (Button) findViewById(R.id.btn_invite_back);
        btn_invite_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        //체크된 유저 초대
        btn_invite_invite = (Button) findViewById(R.id.btn_invite_invite);
        btn_invite_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title =  et_invite_title.getText().toString();
                userId = Long.parseLong(tv_invite_user_id.getText().toString());


                InviteDto inviteDto = new InviteDto(title,callerName,callerId,challengeId,userId,null);
//                InviteDto inviteDto = new InviteDto(title,callerName,callerId,1L,1L,null);
                Call<Integer> call_invite = retrofitInterface.inviteUser(inviteDto);
                call_invite.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            try{
                                System.out.println("초대성공: 제목= "+title+"초청자= "+callerName+"초청자 Id="+callerId+"챌린지 Id= "+challengeId+"유저 Id= "+userId);
                                Toast.makeText(Invite.this, "성공적으로 초대를 보냈습니다", Toast.LENGTH_SHORT).show();

                            }catch (Exception e){
                                System.out.println("유저 초대 단계 예외오류: "+e.getMessage());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        System.out.println("유저 초대 단계 API통신 오류: "+t.toString());
                    }
                });

            }
        });


    }
}