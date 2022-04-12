package com.example.gbt_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;
import com.example.gbt_4.fragments.ChallengeFragment;
import com.example.gbt_4.fragments.CommunityFragment;
import com.example.gbt_4.fragments.HomeFragment;
import com.example.gbt_4.fragments.MyInfoFragment;
import com.example.gbt_4.fragments.StatisticsFragment;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPage extends AppCompatActivity{

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;

    FragmentManager fragmentManager;


    // Fragments 선언
    ChallengeFragment challengeFragment = new ChallengeFragment();
    HomeFragment homeFragment = new HomeFragment();
    StatisticsFragment statisticsFragment = new StatisticsFragment();
    MyInfoFragment myInfoFragment = new MyInfoFragment();
    CommunityFragment communityFragment = new CommunityFragment();

    // 하단 Navi bar 선언
    NavigationBarView bottomNavigationView;

    // Data 전달 bundle 선언
    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        getSupportActionBar().hide();

        bundle = getHttp();

        bottomNavigationView = (NavigationBarView) findViewById(R.id.bottom_navi);

        homeFragment.setArguments(bundle);

        // 하단 Navi bar 구현
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_challenge:
                        challengeFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, challengeFragment).commit();
                        break;
                    case R.id.tab_community:
                        communityFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, communityFragment).commit();
                        break;
                    case R.id.tab_home:
                        homeFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, homeFragment).commit();
                        break;
                    case R.id.tab_info:
                        myInfoFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, myInfoFragment).commit();
                        break;
                    case R.id.tab_statistic:
                        statisticsFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, statisticsFragment).commit();
                        break;
                }
                return true;
            }
        });

    }

    //getHttp 매서드 정의
    private Bundle getHttp() {
        Bundle bundle = new Bundle();

        // retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        // 유저 정보 가져오기 기능
        Call<GetUserDto> call_getUser = retrofitInterface.getByUserId(1);
        call_getUser.enqueue(new Callback<GetUserDto>() {
            @Override
            public void onResponse(Call<GetUserDto> call, Response<GetUserDto> response) {
                if (response.isSuccessful()) {
                    try {
                        GetUserDto getUserDto1 = response.body();
//                        System.out.println(getUserDto1.toString());
                        bundle.putSerializable("user",getUserDto1);

                        //처음 화면 설정
                        getSupportFragmentManager().beginTransaction().add(R.id.frame_lo, homeFragment).commit();
                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetUserDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });

        //유저 1일 흡연량 가져오기 기능
        Call<GetSmokingDto> call_getSmokingDto = retrofitInterface.getTodayCount(1L);
        call_getSmokingDto.enqueue(new Callback<GetSmokingDto>() {
            @Override
            public void onResponse(Call<GetSmokingDto> call, Response<GetSmokingDto> response) {
                try {
                    GetSmokingDto getSmokingDto2 = response.body();
                    bundle.putSerializable("todayCount",getSmokingDto2);

                }catch (Exception e){
                    System.out.println("예외발생!");
                }
            }
            @Override
            public void onFailure(Call<GetSmokingDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });
        //유저 1달 흡연량 가져오기
        Call<GetSmokingListDto> call_getSmokingListDto = retrofitInterface.getMonthCount(1L);
        call_getSmokingListDto.enqueue(new Callback<GetSmokingListDto>() {
            @Override
            public void onResponse(Call<GetSmokingListDto> call, Response<GetSmokingListDto> response) {
                try {
                    GetSmokingListDto getSmokingListDto1 = response.body();
                    bundle.putSerializable("monthCount", getSmokingListDto1);
                }catch (Exception e){
                    System.out.println("예외발생!");
                }
            }
            @Override
            public void onFailure(Call<GetSmokingListDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });
        return bundle;
    }
}