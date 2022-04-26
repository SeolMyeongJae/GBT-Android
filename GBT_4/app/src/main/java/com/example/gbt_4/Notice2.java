package com.example.gbt_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.gbt_4.adapter.InviteAdapter;
import com.example.gbt_4.dto.InviteDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Notice2 extends Activity {

    Button btn_notice_back;


    private InviteAdapter inviteAdapter = null;
    //초대 받는 dto
    private ArrayList<InviteNotice> inviteNotices = new ArrayList<>();
    //초대 보내는 dto
    private ArrayList<InviteDto> invites = new ArrayList<>();
    private static Notice2 instance;




    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notice2);

        RecyclerView recyclerView = findViewById(R.id.rv_notice);

        //뒤로가기 버튼
        btn_notice_back = (Button) findViewById(R.id.btn_invite_back);
        btn_notice_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        inviteAdapter = new InviteAdapter();

        //사용자의 모든 초대 정보 불러오기
        Call<ArrayList<InviteDto>> call_invites = retrofitInterface.getAllInviteByUserId(3L);
        call_invites.enqueue(new Callback<ArrayList<InviteDto>>() {
            @Override
            public void onResponse(Call<ArrayList<InviteDto>> call, Response<ArrayList<InviteDto>> response) {
                if (response.isSuccessful()) {
                    try {
                        System.out.println("초대 리스트 불러오기 성공");
                        invites = response.body();
                        for(int i = 0; i < invites.size(); i++){
                            InviteDto inviteDto = invites.get(i);
                            InviteNotice inviteNotice = new InviteNotice(inviteDto.getCaller(),inviteDto.getTitle(),inviteDto.getCustomChallengeId());
                            inviteNotices.add(inviteNotice);
                        }

                        //data 변경시 화면 변화시키는 매서드
                        inviteAdapter.notifyDataSetChanged();
                    }catch (Exception e){
                        System.out.println("초대 리스트 불러오기 예외오류: "+e.getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<InviteDto>> call, Throwable t) {
                System.out.println("초대 리스트 불러오기 API통신 오류: "+t.toString());
            }
        });

        inviteAdapter.setInvites(inviteNotices);
        inviteAdapter.setItemClickListener(new InviteAdapter.OnItemClickListener() {
            @Override
            public void onClick(@NonNull View v, int position) {
                Long challengeId =inviteNotices.get(position).getCustomChallengeId();
                Intent intent = new Intent(getApplicationContext(),CustomChallengeDetail.class);
                intent.putExtra("challengeId",challengeId);
                startActivity(intent);
            }
        });


        recyclerView.setAdapter(inviteAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public Notice2() {
        instance = (Notice2)this;
    }
    public static Notice2 getInstance() {
        return instance;
    }

    public void deleteNotice(InviteNotice inviteNotice){
        inviteNotices.remove(inviteNotice);
        inviteAdapter.notifyDataSetChanged();
    }
}

