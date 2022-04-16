package com.example.gbt_4.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbt_4.CustomChallengeIng;
import com.example.gbt_4.OfficialChallengeIng;
import com.example.gbt_4.OfficialChallengeVerify;
import com.example.gbt_4.R;
import com.example.gbt_4.RetrofitInterface;
import com.example.gbt_4.dto.AddSmokingDto;
import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {


    TextView tv_userName, tv_comment, tv_todayCount, tv_monthCount;
    Button btn_plus, btn_home_certify;
    ImageView iv_my_official_challenge, iv_my_custom_challenge;

    private final String URL = "http://54.219.40.82/api/";

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = getArguments();
        GetUserDto getUserDto = (GetUserDto) bundle.getSerializable("user");
        GetSmokingDto getSmokingDto = (GetSmokingDto) bundle.getSerializable("todayCount");
        GetSmokingListDto getSmokingListDto = (GetSmokingListDto) bundle.getSerializable("monthCount");

        //retrofit 빌드
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        tv_userName = (TextView) v.findViewById(R.id.tv_home_name);
        tv_comment = (TextView) v.findViewById(R.id.tv_home_comment);
        tv_todayCount = (TextView) v.findViewById(R.id.tv_todayCount);
        tv_monthCount = (TextView) v.findViewById(R.id.tv_monthCount);
        btn_plus = (Button) v.findViewById(R.id.btn_plus);
        btn_home_certify = (Button) v.findViewById(R.id.btn_home_certify);

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_plus:
                        AddSmokingDto addSmokingDto = new AddSmokingDto(1L, "설명재");
                        Call<Integer> call_addSmoking = retrofitInterface.addSmoking(addSmokingDto);
                        call_addSmoking.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                try {
                                    Toast.makeText(getActivity(), "담배 한 개비가 추가되었어요...", Toast.LENGTH_SHORT).show();
                                    tv_todayCount.setText("" + (getSmokingDto.getCount() + 1));
                                    getSmokingDto.setCount(getSmokingDto.getCount() + 1);
                                    tv_monthCount.setText("" + (getSmokingListDto.getTotal() + 1));
                                    getSmokingListDto.setTotal(getSmokingListDto.getTotal() + 1);

                                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                    ft.detach(HomeFragment.this).attach(HomeFragment.this).commit();

//                                    getParentFragmentManager().beginTransaction().detach(HomeFragment.this).attach(HomeFragment.this).commit();
                                } catch (Exception e) {
                                }
                            }
                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                System.out.println("***********" + t.toString());
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });

        btn_home_certify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), OfficialChallengeVerify.class);
                startActivity(intent1);
            }
        });

        //받아온 번들로 담배 count Data 삽입
        try {
            tv_userName.setText(getUserDto.getUserName());
            tv_comment.setText(getUserDto.getComment());
            tv_todayCount.setText(getSmokingDto.getCount().toString());
            tv_monthCount.setText((getSmokingListDto.getTotal().toString()));
        }catch (NullPointerException e){
            tv_todayCount.setText("0");
            tv_monthCount.setText("0");
            tv_userName.setText("설명재");
            tv_comment.setText("유저 상태 메세지");
        }

//        iv_my_official_challenge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(getActivity(), OfficialChallengeIng.class);
//                startActivity(intent1);
//            }
//        });
//
//        iv_my_custom_challenge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(getActivity(), CustomChallengeIng.class);
//                startActivity(intent2);
//            }
//        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}