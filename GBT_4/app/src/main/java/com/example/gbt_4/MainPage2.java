package com.example.gbt_4;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPage2 extends AppCompatActivity {

    NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bottomNavigationView = (NavigationBarView) findViewById(R.id.bottom_navi);

        //처음 화면 설정
        getSupportFragmentManager().beginTransaction().add(R.id.frame_lo, new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_challenge:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, new ChallengeFragment()).commit();
                        break;
                    case R.id.tab_community:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, new CommunityFragment()).commit();
                        break;
                    case R.id.tab_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, new HomeFragment()).commit();
                        break;
                    case R.id.tab_info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, new MyInfo()).commit();
                        break;
                    case R.id.tab_statistic:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lo, new StatisticsFragment()).commit();
                        break;
                }
                    return true;
                }
        });
    }
}