package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InputInfo extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "InputInfoLog";
    private final String URL = "http://54.219.40.82/api/";

//    버튼 정의
    private Button btn_submit, btn_select_year;
//    입력창 정의
    private EditText et_nickname,et_gender, et_smoking_amount, et_smoking_year,et_price, et_comment;
//    텍스트뷰 정의
    private TextView tv_birthYear;
//    문자열 정의
    private String nickname, gender, comment, profileImg, popupImg;
//    숫자열 정의
    private int birthYear, smokingYear, price, smokingAmount;

//    레트로핏 객체 생성
    private Retrofit retrofit;
//    인터페이스 객체 생성
    private InputInfoInterface inputInfoInterface;


//    날짜선택창 객체 생성
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Log.d("YearMonthPickerTest", "year = " + year + ", month = " + monthOfYear + ", day = " + dayOfMonth);
            String yearStr = Integer.toString(year);
            tv_birthYear.setText(yearStr);
            birthYear = Integer.parseInt(tv_birthYear.getText().toString());

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info);

        firstInit();
        btn_submit.setOnClickListener(this);
    }

    public void firstInit() {
//        버튼 id값 부여
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_select_year = findViewById(R.id.btn_select_year);
//        입력창 id값 부여
        et_nickname = (EditText)findViewById(R.id.et_nickname);
        et_gender = (EditText)findViewById(R.id.et_gender);
        et_price = (EditText)findViewById(R.id.et_price);
        et_comment = (EditText)findViewById(R.id.et_comment);
        et_smoking_amount = (EditText)findViewById(R.id.et_smoking_amount);
        et_smoking_year = (EditText)findViewById(R.id.et_smoking_year);
//        텍스트뷰 id값 부여
        tv_birthYear = (TextView) findViewById(R.id.tv_birthYear);



        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        inputInfoInterface = retrofit.create(InputInfoInterface.class);

        btn_select_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectYear selectYear = new SelectYear();
                selectYear.setListener(d);
                selectYear.show(getSupportFragmentManager(),"출생년도 선택");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
                nickname = et_nickname.getText().toString();
                gender = et_gender.getText().toString();
                price = Integer.parseInt(et_price.getText().toString());
                comment = et_comment.getText().toString();
                smokingYear = Integer.parseInt(et_smoking_year.getText().toString());
                smokingAmount = Integer.parseInt(et_smoking_amount.getText().toString());
                profileImg = "더미데이터";
                popupImg = "더미데이터";

//              addUserDto 사용
                AddUserDto addUserDto = new AddUserDto(nickname, gender, birthYear, smokingYear,comment,price,smokingAmount,profileImg,popupImg);
                System.out.println(addUserDto);




                Call<AddUserDto> call_post = inputInfoInterface.addUser(addUserDto);
                call_post.enqueue(new Callback<AddUserDto>() {
                                      @Override
                                      public void onResponse(Call<AddUserDto> call, Response<AddUserDto> response) {
                                          if (response.isSuccessful()) {
                                              try {
                                                  int result = Integer.parseInt(response.body().toString());
                                                  Log.v(TAG, "result = " + result);
                                                  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
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
                                    public void onFailure(Call<AddUserDto> call, Throwable t) {
                                        Log.v(TAG, "Fail");
                                        System.out.println("***********" + t.toString());
                                        Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                                    }
                                    
                });
                break;
            default:
                break;
        }
        Intent intent = new Intent(getApplicationContext(),MainPage.class);
        startActivity(intent);


//        btn_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),MainPage.class);
//                startActivity(intent);
//
//            }
//        });


    }
}