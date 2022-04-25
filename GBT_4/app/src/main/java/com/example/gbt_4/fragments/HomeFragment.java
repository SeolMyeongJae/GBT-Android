package com.example.gbt_4.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbt_4.OfficialChallengeIng;
import com.example.gbt_4.TodayAttend;
import com.example.gbt_4.R;
import com.example.gbt_4.RetrofitInterface;
import com.example.gbt_4.dto.AddSmokingDto;
import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment{

    private SharedPreferences sharedPreferences;

    TextView tv_userName, tv_comment, tv_todayCount, tv_monthCount,btn_home_go_official_challenge_ing, btn_home_go_custom_challenge_ing;
    Button btn_home_plus, btn_home_attend;
    Long count;

    private final String URL = "http://54.219.40.82/api/";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("userId",MODE_PRIVATE);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //ID값 부여
        tv_userName = (TextView) v.findViewById(R.id.tv_home_name);
        tv_comment = (TextView) v.findViewById(R.id.tv_home_comment);
        tv_todayCount = (TextView) v.findViewById(R.id.tv_today_count);
        btn_home_plus = (Button) v.findViewById(R.id.btn_home_plus);
        btn_home_attend = (Button) v.findViewById(R.id.btn_home_attend);
        btn_home_go_official_challenge_ing = (TextView) v.findViewById(R.id.btn_home_go_official_challenge_ing);
        btn_home_go_custom_challenge_ing = (TextView) v.findViewById(R.id.btn_home_go_custom_challenge_ing);

        // 유저 정보 가져오기 기능
        Call<GetUserDto> call_getUser = retrofitInterface.getByUserId(1L);
        call_getUser.enqueue(new Callback<GetUserDto>() {
            @Override
            public void onResponse(Call<GetUserDto> call, Response<GetUserDto> response) {
                if (response.isSuccessful()) {
                    try {
                        Long userId;
                        GetUserDto getUserDto = response.body();
                        tv_comment.setText(getUserDto.getComment());
                        tv_userName.setText(getUserDto.getUserName());

                        //userID를 내부 저장소에 저장
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putLong("userId",getUserDto.getUserId());
                        editor.putString("myName",getUserDto.getUserName());
                        editor.commit();

                        userId = sharedPreferences.getLong("userId",-1);
                        System.out.println("22222222222222222222222222222222"+userId);

                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
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
                    GetSmokingDto getSmokingDto = response.body();

                    if (getSmokingDto == null){
                        tv_todayCount.setText("0");
                    } else {
                        tv_todayCount.setText(getSmokingDto.getCount().toString()+"개비");
                    }
                }catch (Exception e){
                    System.out.println("예외발생!");
                }
            }
            @Override
            public void onFailure(Call<GetSmokingDto> call, Throwable t) {
                System.out.println("***********" + t.toString());
            }
        });

        //출석 버튼
        btn_home_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), TodayAttend.class);
                startActivity(intent1);
            }
        });


        //담배 추가 버튼
        btn_home_plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_home_plus:
                        AddSmokingDto addSmokingDto = new AddSmokingDto(1L);
                        Call<Long> call_addSmoking = retrofitInterface.addSmoking(addSmokingDto);
                        call_addSmoking.enqueue(new Callback<Long>() {
                            @Override
                            public void onResponse(Call<Long> call, Response<Long> response) {
                                if (response.isSuccessful()) {
                                    try {
                                        System.out.println("API 통신은 되는데,,");
                                        Toast.makeText(getActivity(), "담배 한 개비가 추가되었어요...", Toast.LENGTH_SHORT).show();
                                        tv_todayCount.setText(response.body().toString()+"개비");

                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }else {
                                    System.out.println(response.errorBody());
                                }
                            }
                            @Override
                            public void onFailure(Call<Long> call, Throwable t) {
                                System.out.println("통신 실패:" + t.toString());
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });

        //공식 챌린지 바로가기
        btn_home_go_official_challenge_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),OfficialChallengeIng.class);
                startActivity(intent);
            }
        });


        btn_home_go_custom_challenge_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                CustomChallengeFragment customChallengeFragment = new CustomChallengeFragment();
                fragmentTransaction.replace(R.id.frame_lo,customChallengeFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });




        return v;
    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_home_plus:
//                Call<Long> call_addSmoking = retrofitInterface.addSmoking(1L);
//                call_addSmoking.enqueue(new Callback<Long>() {
//                    @Override
//                    public void onResponse(Call<Long> call, Response<Long> response) {
//                        if (response.isSuccessful()) {
//                            try {
//                                Toast.makeText(getActivity(), "담배 한 개비가 추가되었어요...", Toast.LENGTH_SHORT).show();
//                                tv_todayCount.setText(count.toString());
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());
//                            }
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<Long> call, Throwable t) {
//                        System.out.println("***********" + t.toString());
//                    }
//                });
//                break;
//            default:
//                break;
//        }
//    }


    private HttpLoggingInterceptor httpLoggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                android.util.Log.e("MyGitHubData :", message + "");
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}