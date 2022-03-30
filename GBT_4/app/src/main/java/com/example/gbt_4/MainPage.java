package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPage extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "Main_Page_Log:";
    private final String URL = "http://54.219.40.82/api/";

    private TextView tv_userName, tv_comment;

    private String userName, comment, gender, profileImg, popupImg;

    private Long userId, smokingYear, price, averageSmoking;

    private Date birthYear;

    private Retrofit retrofit;

    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        firstInit();
    }

    public void firstInit(){
        tv_userName = (TextView) findViewById(R.id.tv_userName);
        tv_comment = (TextView) findViewById(R.id.tv_comment);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        GetUserDto getUserDto = new GetUserDto(userId, userName, gender, birthYear, smokingYear, comment, price, averageSmoking, profileImg, popupImg);

        Call<GetUserDto> call_get = retrofitInterface.getByUserId(1);
        call_get.enqueue(new Callback<GetUserDto>() {
            @Override
            public void onResponse(Call<GetUserDto> call, Response<GetUserDto> response) {
                if (response.isSuccessful()) {
                    try {
                        GetUserDto data = response.body();
//                        int result = Integer.parseInt(response.body().toString());
//                        Log.v(TAG, "result = " + result);
                       comment = data.getComment();
                       userName = data.getUserName();
                        System.out.println(comment);
                        System.out.println(userName);
//
//
                        tv_comment.setText(comment);
                        tv_userName.setText(userName);

                    }catch (NumberFormatException e) {
                        e.printStackTrace();
//
//                        System.out.println(comment);
//                        System.out.println(userName);
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

                System.out.println(comment);
                System.out.println(userName);
                Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
            }
        });
//        System.out.println(getUserDto);
//        tv_comment.setText(getUserDto.getComment());
//        tv_userName.setText(getUserDto.getUserName());

    }

    @Override
    public void onClick(View view) {

    }
}