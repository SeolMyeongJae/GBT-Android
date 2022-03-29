package com.example.gbt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class InputInfo extends AppCompatActivity {

    Button btn_submit, btn_select_year;
    TextView tv_birthYear;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Log.d("YearMonthPickerTest", "year = " + year + ", month = " + monthOfYear + ", day = " + dayOfMonth);
            String yearStr = Integer.toString(year);
            tv_birthYear.setText(yearStr);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_info);

        tv_birthYear = (TextView) findViewById(R.id.tv_birthYear);

        btn_select_year = findViewById(R.id.btn_select_year);
        btn_select_year.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SelectYear selectYear = new SelectYear();
                selectYear.setListener(d);
                selectYear.show(getSupportFragmentManager(),"출생년도 선택");
//                tv_birthYear.setText(SelectYear.year);
//                String year = Integer.toString(selectYear.birthYear);
//                tv_birthYear.setText(year);

            }
        });

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                startActivity(intent);

            }
        });
    }
}