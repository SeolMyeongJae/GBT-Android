//package com.example.gbt_4;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.Window;
//
//import com.example.gbt_4.adapter.InviteAdapter;
//import com.example.gbt_4.dto.InviteDto;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class Notice2 extends AppCompatActivity {
//
//
//    private InviteAdapter inviteAdapter = null;
//    private ArrayList<InviteNotice> inviteNotices = new ArrayList<>();
//
//
//    private final String URL = "http://54.219.40.82/api/";
//    private Retrofit retrofit;
//    private RetrofitInterface retrofitInterface;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_notice);
//
//
//        //retrofit 빌드
//        retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//
//        Call<ArrayList<InviteDto>> call_invites = retrofitInterface.getAllInviteByUserId(1L);
//        call_invites.enqueue(new Callback<ArrayList<InviteDto>>() {
//            @Override
//            public void onResponse(Call<ArrayList<InviteDto>> call, Response<ArrayList<InviteDto>> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        System.out.println("초대 리스트 불러오기 성공");
//                        ArrayList<InviteDto> data = response.body();
//                        for(int i = 0; i <= data.size(); i++){
//                            InviteDto inviteDto = data.get(i);
//                            InviteNotice inviteNotice = new InviteNotice(inviteDto.getCaller(),inviteDto.getTitle(),inviteDto.getCustomChallengeId());
//                            inviteNotices.add(inviteNotice);
//                        }
//                    }catch (Exception e){
//                        System.out.println("초대 리스트 불러오기 예외오류: "+e.getMessage());
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<ArrayList<InviteDto>> call, Throwable t) {
//                System.out.println("초대 리스트 불러오기 API통신 오류: "+t.toString());
//
//            }
//        });
//
//        RecyclerView recyclerView = findViewById(R.id.rv_notice);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        inviteAdapter = new InviteAdapter(inviteNotices);
//        recyclerView.setAdapter(inviteAdapter);
//
//
//
//
//
//
//    }
//}