package com.example.gbt_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gbt_4.info.PracticeDto;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPage3 extends AppCompatActivity {
    private final String TAG = "Main_Page_Log:";
    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;


    ChallengeFragment challengeFragment = new ChallengeFragment();
    HomeFragment homeFragment = new HomeFragment();
    StatisticsFragment statisticsFragment = new StatisticsFragment();
    MyInfo myInfo = new MyInfo();
    CommunityFragment communityFragment = new CommunityFragment();



    NavigationBarView bottomNavigationView;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bundle = FirstInit();

        bottomNavigationView = (NavigationBarView) findViewById(R.id.bottom_navi);

        homeFragment.setArguments(bundle);




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
                        myInfo.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, myInfo).commit();
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

    private Bundle FirstInit() {
        Bundle bundle = new Bundle();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<GetUserDto> call_get = retrofitInterface.getByUserId(1);
        call_get.enqueue(new Callback<GetUserDto>() {
            @Override
            public void onResponse(Call<GetUserDto> call, Response<GetUserDto> response) {
                if (response.isSuccessful()) {
                    try {
                        GetUserDto data = response.body();
                        System.out.println(data.toString());
                        bundle.putSerializable("user",data);
                        //처음 화면 설정
                        getSupportFragmentManager().beginTransaction().add(R.id.frame_lo, homeFragment).commit();


                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.v(TAG, "error = " + String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetUserDto> call, Throwable t) {
                Log.v(TAG, "Fail");
                System.out.println("***********" + t.toString());
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
            }
        });
        return bundle;
    }
}